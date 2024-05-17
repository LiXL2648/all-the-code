package com.li.config.lock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author LiXL
 * @date 2024/4/15
 */
@Component
public class DistributedLock {

    private String uuid;

    public DistributedLock() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public DistributedRedisLock getDistributedRedis(String lockName) {
        return new DistributedRedisLock(stringRedisTemplate, lockName, uuid);
    }
}
