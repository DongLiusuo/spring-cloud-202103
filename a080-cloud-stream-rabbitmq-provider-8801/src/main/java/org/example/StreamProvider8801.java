package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamProvider8801 {

    public static void main(String[] args) {
        SpringApplication.run(StreamProvider8801.class, args);
    }
}
