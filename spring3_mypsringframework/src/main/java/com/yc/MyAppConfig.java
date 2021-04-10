package com.yc;


import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyCompenentScan;
import com.yc.springframework.stereotype.MyConfiguration;

@MyConfiguration
@MyCompenentScan(basePackages = {"com.yc.bean","com.yc.biz"})
public class MyAppConfig {
    @MyBean
    public HelloWorld hw(){
        return new HelloWorld();
    }
    @MyBean
    public HelloWorld hw2(){
        return new HelloWorld();
    }


}
