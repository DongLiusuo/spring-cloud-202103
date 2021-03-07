package org.example.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableEurekaClient // 说明这是一个euraka的客户端
public class ApplicationContextConfiguration {


    @Bean
    @LoadBalanced // 开启负载均衡，默认为轮询
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
