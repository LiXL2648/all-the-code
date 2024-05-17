package com.li.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.li.config.lock.DistributedLock;
import com.li.config.lock.DistributedRedisLock;
import com.li.config.zookeeper.DistributedZKLock;
import com.li.config.zookeeper.ZKClient;
import com.li.empty.Lock;
import com.li.empty.Stock;
import com.li.mapper.LockMapper;
import com.li.mapper.StockMapper;
import com.li.service.StockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiXL
 * @date 2024/3/16
 */
@Service
// @Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    ReentrantLock lock = new ReentrantLock();

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DistributedLock distributedLock;

    @Resource
    private ZKClient zkClient;

    @Resource
    private CuratorFramework curatorFramework;

    @Resource
    private LockMapper lockMapper;

    @Override
    public void checkAndLock() {
        Lock lock = new Lock().setLockName("lock");
        try {
            lockMapper.insert(lock);
            // 查询库存
            String stock = stringRedisTemplate.opsForValue().get("stock");
            // 判断库存
            if (StringUtils.isNotBlank(stock)) {
                int count = Integer.parseInt(stock);
                if (count > 0) {
                    // 扣减库存
                    stringRedisTemplate.opsForValue().set("stock", String.valueOf(--count));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 重试
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            checkAndLock();
        } finally {
            lockMapper.deleteById(lock.getId());
        }
    }

    public void checkAndLock9() {
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/curator/locks");
        try {
            mutex.acquire();
            // 查询库存
            String stock = stringRedisTemplate.opsForValue().get("stock");
            // 判断库存
            if (StringUtils.isNotBlank(stock)) {
                int count = Integer.parseInt(stock);
                if (count > 0) {
                    // 扣减库存
                    stringRedisTemplate.opsForValue().set("stock", String.valueOf(--count));
                    testReentrant(mutex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testReentrant(InterProcessMutex mutex) throws Exception {
        mutex.acquire();
        System.out.println("测试可重入");
        mutex.release();
    }

    public void checkAndLock8() {
        DistributedZKLock lock = zkClient.getLock("lock");
        lock.lock();
        try {
            // 查询库存
            String stock = stringRedisTemplate.opsForValue().get("stock");
            // 判断库存
            if (StringUtils.isNotBlank(stock)) {
                int count = Integer.parseInt(stock);
                if (count > 0) {
                    // 扣减库存
                    stringRedisTemplate.opsForValue().set("stock", String.valueOf(--count));
                    testZKLock();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void testZKLock() {
        DistributedZKLock lock = zkClient.getLock("lock");
        lock.lock();
        System.out.println("测试可重入");
        lock.unlock();
    }

    public void checkAndLock7() {
        DistributedRedisLock redisLock = distributedLock.getDistributedRedis("lock");
        redisLock.lock();
        try {
            // 查询库存
            String stock = stringRedisTemplate.opsForValue().get("stock");
            // 判断库存
            if (StringUtils.isNotBlank(stock)) {
                int count = Integer.parseInt(stock);
                if (count > 0) {
                    // 扣减库存
                    stringRedisTemplate.opsForValue().set("stock", String.valueOf(--count));
                    // testRedisLock();
                    /*try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/
                }
            }
        } finally {
            redisLock.unlock();
        }
    }

    public void testRedisLock() {
        DistributedRedisLock redisLock = distributedLock.getDistributedRedis("lock");
        redisLock.lock();
        System.out.println("testLock");
        redisLock.unlock();
    }

    public void checkAndLock6() {
        // 加锁
        String uuid = UUID.randomUUID().toString();
        while (Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS))) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            String stock = stringRedisTemplate.opsForValue().get("stock");
            if (StringUtils.isNotBlank(stock)) {
                int count = Integer.parseInt(stock);
                if (count > 0) {
                    stringRedisTemplate.opsForValue().set("stock", String.valueOf(--count));
                }
            }
        } finally {
            // 解锁
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList("lock"), uuid);
            /*if (uuid.equals(stringRedisTemplate.opsForValue().get("lock"))) {
                stringRedisTemplate.delete("lock");
            }*/
        }

    }

    public void checkAndLock5() {
        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public String execute(RedisOperations operations) throws DataAccessException {
                // watch
                operations.watch("stock");
                String stock = (String) operations.opsForValue().get("stock");
                if (StringUtils.isNotBlank(stock)) {
                    int count = Integer.parseInt(stock);
                    if (count > 0) {
                        // multi
                        operations.multi();
                        operations.opsForValue().set("stock", String.valueOf(--count));
                        // exec
                        List exec = operations.exec();
                        // 失败重试
                        if (CollectionUtils.isEmpty(exec)) {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            checkAndLock();
                        }
                    }
                }
                return null;
            }
        });
    }

    public void checkAndLock4() {
        Stock stock = stockMapper.selectOne(new LambdaQueryWrapper<Stock>().eq(Stock::getProductCode, "1001"));
        if (stock != null && stock.getCount() > 0) {
            Integer version = stock.getVersion();
            stock.setCount(stock.getCount() - 1);
            stock.setVersion(version + 1);
            int update = stockMapper.update(stock, new LambdaQueryWrapper<Stock>().eq(Stock::getProductCode, "1001").eq(Stock::getVersion, version));
            if (update == 0) {
                checkAndLock();
            }
        }
    }

    @Transactional
    public void checkAndLock3() {
        Stock stock = stockMapper.selectForUpdate("1001");
        if (stock != null && stock.getCount() > 0) {
            stock.setCount(stock.getCount() - 1);
            stockMapper.updateById(stock);
        }
    }

    public void checkAndLock2() {
        stockMapper.updateStock("1001", 1);
    }

    // @Transactional
    // @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void checkAndLock1() {
        lock.lock();
        try {
            // 查库存
            Stock stock = stockMapper.selectOne(new LambdaQueryWrapper<Stock>().eq(Stock::getProductCode, "1001"));
            // Stock stock = stockMapper.selectForUpdate("1001");
            // 判断库存
            if (stock != null && stock.getCount() > 0) {
                stock.setCount(stock.getCount() - 1);
                // 减库存
                stockMapper.updateById(stock);
            }
        } finally {
            lock.unlock();
        }
    }
}
