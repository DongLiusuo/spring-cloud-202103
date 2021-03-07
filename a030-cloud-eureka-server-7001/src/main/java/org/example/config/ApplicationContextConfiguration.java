package org.example.config;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaServer  // 说明这是一个eureka服务端
public class ApplicationContextConfiguration {

}
