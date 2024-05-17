package com.li.config.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * @author LiXL
 * @date 2024/4/21
 */
public class DistributedZKLock implements Lock {

    private ZooKeeper zooKeeper;

    private String lockName;

    private String currentNodePath;

    // 存放分布式锁的根路径
    private static final String ROOT_PATH = "/locks";

    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public DistributedZKLock(ZooKeeper zooKeeper, String lockName) {
        this.zooKeeper = zooKeeper;
        this.lockName = lockName;
        // 根节点初始化
        try {
            if (zooKeeper.exists(ROOT_PATH, false) == null) {
                zooKeeper.create(ROOT_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        tryLock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        // 创建znode节点的操作
        // 判断 threaLocal中是否已经有锁，有锁直接重入
        Integer count = THREAD_LOCAL.get();
        if (count!= null && count > 0) {
            THREAD_LOCAL.set(count + 1);
            return true;
        }
        try {
            // 防止死锁问题，因此创建临时节点
            // 实现阻塞锁，利用临时序列化
            currentNodePath = zooKeeper.create(ROOT_PATH + "/" + lockName + "-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 获取前置节点，如果前置节点为空，则获取锁成功，否则监听前置节点
            String preNode = getPreNode();
            if (preNode != null) {
                // 使用闭锁实现阻塞
                CountDownLatch countDownLatch = new CountDownLatch(1);
                // 再次判断前置节点是否存在，因为获取前置节点操作不具备原子性
                if (zooKeeper.exists(ROOT_PATH + "/" + preNode, watchedEvent -> {
                    countDownLatch.countDown();
                }) == null) {
                    THREAD_LOCAL.set(1);
                    return true;
                }
                countDownLatch.await();
            }
            THREAD_LOCAL.set(1);
        } catch (Exception e) {
            e.printStackTrace();
            /*try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            // 自选，重新获取连接
            tryLock();*/
        }
        return true;
    }

    private String getPreNode() throws Exception {
        // 获取根节点下的所有节点
        List<String> children = zooKeeper.getChildren(ROOT_PATH, false);
        if (CollectionUtils.isEmpty(children)) {
            throw new IllegalMonitorStateException("非法操作");
        }

        // 获取和当前节点同一资源的锁
        children = children.stream().filter(node -> StringUtils.startsWith(node, lockName + "-")).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(children)) {
            throw new IllegalMonitorStateException("非法操作");
        }

        // 对锁进行排序
        Collections.sort(children);

        // 获取当前节点的下标
        String currentNode = StringUtils.substringAfterLast(currentNodePath, "/");
        int index = Collections.binarySearch(children, currentNode);
        if (index < 0) {
            throw new IllegalMonitorStateException("非法操作");
        } else if (index > 0) {
            // 返回前置节点
            return children.get(index - 1);
        }
        //如果当前节点就是第一个节点，则返回 null
        return null;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 删除znode节点的操作
        try {
            THREAD_LOCAL.set(THREAD_LOCAL.get() - 1);
            if (THREAD_LOCAL.get() == 0) {
                zooKeeper.delete(currentNodePath, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
