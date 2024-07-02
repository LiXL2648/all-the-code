package com.li.cloud.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

// 自定义条件Filter
// 1. 新建类名XXX需要以GatewayFilterFactory结尾
// 并继承AbstractGatewayFilterFactory类
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

    // 5. 空参构造方法，内部调用super
    public MyGatewayFilterFactory() {
        super(MyGatewayFilterFactory.Config.class);
    }

    // 3. 重写apply方法
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            System.out.println("进入自定义网关过滤器MyGatewayFilterFactory，status===="+config.getStatus());
            if(request.getQueryParams().containsKey("li")) {
                return chain.filter(exchange);
            }else {
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                return exchange.getResponse().setComplete();
            }
        };
    }

    // 4. 重写shortcutFieldOrder
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("status");
    }

    // 2. 新建XxxGatewayFilterFactory.Config内部类
    static class Config {
        @Setter
        @Getter
        private String status;
    }
}
