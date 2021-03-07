package org.example.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient // 说明这是一个rureka的客户端
public class ApplicationContextConfiguration {

}
