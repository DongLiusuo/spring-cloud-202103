package org.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义gateway的过滤器
 * 实现两个接口的方法
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Gateway的自定义过滤器");
        log.info("request对象：" + exchange.getRequest());
        // 此处可以用request参数作鉴权等动作

        //如果鉴权不通过给人家回应      设置一个状态码
        //exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);

        return chain.filter(exchange);
    }

    /**
     * 加载过滤器优先级，越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
