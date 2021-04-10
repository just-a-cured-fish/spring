package com.yc.test;

import com.yc.Appconfig;
import com.yc.biz.HelloWorld;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldTest extends TestCase {
    private ApplicationContext ac;
    @Override
    @Before
    public void setUp(){
        ac=new AnnotationConfigApplicationContext(Appconfig.class);
    }
    @Test
    public void testHello(){
        HelloWorld hw=(HelloWorld)ac.getBean("helloWorld");
        hw.hello();
    }





}
