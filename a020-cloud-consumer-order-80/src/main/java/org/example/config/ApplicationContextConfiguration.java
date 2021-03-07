package org.example.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfiguration {


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
