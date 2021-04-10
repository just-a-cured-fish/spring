package com.yc;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogAspect  implements MethodInterceptor {

    private Object target;
    public  LogAspect(Object object){
        this.target=object;
    }
    public  Object createProxy(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

         public  void logBofore(){
        System.out.println("======before advice=====");

         }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
       if(method.getName().startsWith("add")){
           logBofore();
       }
       Object returnValue=method.invoke(this.target,args);

        return returnValue;
    }
}
