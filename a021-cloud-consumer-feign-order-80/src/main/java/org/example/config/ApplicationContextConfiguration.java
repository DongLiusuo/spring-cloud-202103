package org.example.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.example.service") // 配置OpenFeign
public class ApplicationContextConfiguration {

}
