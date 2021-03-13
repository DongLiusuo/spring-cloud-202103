package org.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    /**
     * 配置了一个 id 为 "toBaiduGuoNei" 的路由规则，当访问地址 http://localhost:9527/guonei 时会自动转发到地址：http://news.baidu.com/guonei
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route(
                "path_routr_baidu_news",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")
        ).build();

        return routes.build();

    }

}
