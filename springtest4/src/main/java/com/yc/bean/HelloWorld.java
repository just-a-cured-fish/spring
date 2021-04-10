package com.yc.bean;


import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestory;

@MyComponent
public class HelloWorld {
    @MyPostConstruct
    public void setup(){
        System.out.println("MyPostConstruct");
    }
    @MyPreDestory
    public void destroy(){
        System.out.println("MyproDestroy");
    }
    public  HelloWorld(){
        System.out.println("hello world 构造");
    }
    public  void show (){
        System.out.println("show");
    }
}
