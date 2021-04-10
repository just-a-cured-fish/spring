package com.yc;


import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyConfiguration;
import com.yc.springframework.stereotype.MyCompenentScan;

@MyConfiguration
@MyCompenentScan(basePackages = {"com.yc.biz","com.yc.bean"})
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
