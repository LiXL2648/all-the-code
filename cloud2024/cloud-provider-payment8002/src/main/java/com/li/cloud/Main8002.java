package com.li.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiXL
 * @date ${DATE}
 */
@SpringBootApplication
@MapperScan("com.li.cloud.mapper")
@EnableDiscoveryClient //服务注册和发现
@RefreshScope // 动态刷新
public class Main8002 {
    public static void main(String[] args) {
        SpringApplication.run(Main8002.class, args);
    }
}