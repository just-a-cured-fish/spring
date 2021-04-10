package com.yc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@ComponentScan(basePackages = {"com.yc.huawei","com.yc.mimi"})
public class AppConfig {
    @Bean
    public Random r(){
        return new Random();
    }

}
