package org.example.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient // 说明这是一个euraka的客户端
@EnableFeignClients(basePackages = "org.example.service") // 配置OpenFeign
public class ApplicationContextConfiguration {

}
