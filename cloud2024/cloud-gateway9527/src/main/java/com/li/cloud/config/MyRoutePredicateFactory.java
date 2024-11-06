package com.li.cloud.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
// 自定义断言
// 1. 新建类名XXX需要以RoutePredicateFactory结尾
// 并继承AbstractRoutePredicateFactory类
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    // 4. 空参构造方法，内部调用super
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    // 2. 重写apply方法
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            //检查request的参数里面，userType是否为指定的值，符合配置就通过
            String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
            return userType != null && userType.equals(config.getUserType());
        };
    }

    // 3. 新建apply方法所需要的静态内部类MyRoutePredicateFactory.Config
    // 这个Config类就是我们的路由断言规则，重要
    @Validated
    public static class Config {

        @Setter
        @Getter
        @NotEmpty
        private String userType; //钻、金、银等用户等级
    }

    // 支持段格式
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}
