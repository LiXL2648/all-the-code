package com.li.config.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiXL
 * @date 2024/5/16
 */
@Configuration
public class CuratorConfig {

    @Bean
    public CuratorFramework curatorFramework() {
        // 初始化重试策略，这里使用的是指数补偿策略，初始间隔时间，重试次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        // 初始化Curator客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.26.48:2181", retryPolicy);
        // 手动启动，否则很多功能都无法正常使用
        client.start();
        return client;
    }
}
