package com.yc;

import javax.jws.Oneway;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogAspect  implements InvocationHandler {

    private Object target;
    public  LogAspect(Object object){
        this.target=object;
    }
    public  Object createProxy(){
    return  Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类的对象:"+proxy.getClass());
        System.out.println("代理类的方法:"+method);
        System.out.println("代理类的参数:"+args);
    if(method.getName().startsWith("add")){
        logBofore();
    }
        Object returnValue=method.invoke(this.target,args);
         return  returnValue;

         }
         public  void logBofore(){
        System.out.println("======before advice=====");

         }
}
