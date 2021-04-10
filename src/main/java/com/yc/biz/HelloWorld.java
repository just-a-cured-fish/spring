package com.yc.biz;


import org.springframework.stereotype.Component;

@Component()
public class HelloWorld {
    public HelloWorld(){
        System.out.println("无参构造方法");
    }
    public void hello(){
        System.out.println("hello world");
    }
}

