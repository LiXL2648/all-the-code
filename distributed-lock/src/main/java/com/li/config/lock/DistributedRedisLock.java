package com.li.config.lock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author LiXL
 * @date 2024/4/15
 */
public class DistributedRedisLock implements Lock {

    private StringRedisTemplate stringRedisTemplate;

    private String lockName;

    private String uuid;

    private long expire = 30l;

    public DistributedRedisLock(StringRedisTemplate stringRedisTemplate, String lockName, String uuid) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuid = uuid + "-" + Thread.currentThread().getId();;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
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
        try {
            return tryLock(-1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 加锁
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        this.expire = time == -1L ? this.expire : unit.toSeconds(time);
        String script = "if redis.call('exists', KEYS[1]) == 0 or redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                        "then " +
                        "   redis.call('hincrby', KEYS[1], ARGV[1], 1) " +
                        "   redis.call('expire', KEYS[1], ARGV[2]) " +
                        "   return 1 " +
                        "else " +
                        "   return 0 " +
                        "end";
        // 获取锁失败，尝试继续获取锁
        while (!Boolean.TRUE.equals(stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList(lockName), this.uuid, String.valueOf(this.expire)))) {
            Thread.sleep(50);
        }
        renewExpire();
        return true;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        String script = "if redis.call('hexists', KEYS[1], ARGV[1]) == 0 " +
                        "then " +
                        "   return nil " +
                        "elseif redis.call('hincrby', KEYS[1], ARGV[1], -1) == 0 " +
                        "then " +
                        "   return redis.call('del', KEYS[1]) " +
                        "else " +
                        "   return 0 " +
                        "end";
        if (stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Collections.singletonList(lockName), uuid) == null) {
            throw new IllegalMonitorStateException("this lock doesn't belong to you");
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 自动续期
     */
    private void renewExpire() {
        String script = "if redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                "then " +
                "   return redis.call('expire', KEYS[1], ARGV[2]) " +
                "else " +
                "   return 0 end";
        // 开启定时器
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Boolean execute = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList(lockName), uuid, String.valueOf(expire));
                if (Boolean.TRUE.equals(execute)) {
                    // 更新成功，则继续开启定时器
                    renewExpire();
                }
            }
        }, this.expire * 1000 / 3);
    }
}
