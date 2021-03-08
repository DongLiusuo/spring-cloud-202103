package org.example.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient // 也可以使用@EnableEurekaClient
@EnableCircuitBreaker // 激活Hystrix断路器
public class ApplicationContextConfiguration {

}
