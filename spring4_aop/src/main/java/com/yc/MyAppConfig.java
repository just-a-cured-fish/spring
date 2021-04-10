package com.yc;


import com.yc.bean.HelloWorld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableAspectJAutoProxy
public class MyAppConfig {
    @Bean
    public HelloWorld hw(){
        return new HelloWorld();
    }
    @Bean
    public HelloWorld hw2(){
        return new HelloWorld();
    }


}
