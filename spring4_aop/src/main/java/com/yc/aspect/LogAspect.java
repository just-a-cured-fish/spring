package com.yc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@Aspect
@Component

public class LogAspect {

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")
    private  void add(){

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private void update(){

    }
    @Pointcut("add() || update()")
    private  void addAndUpdate(){

    }
    @Before("com.yc.aspect.LogAspect.addAndUpdate()")
    public  void log(){
        System.out.println("=========前置增强的日志======");
        Date d=new Date();
        SimpleDateFormat format=new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(d));
        System.out.println("=========签置增强结束======");
    }
    @After("execution(* com.yc.biz.StudentBizImpl.find*(..))")      //spring是一个ioc容器，它可以使用 di将程序运行的信息注入
    private void bye2( JoinPoint jp) {
        System.out.println("===bye===");
        //连接点的所有的信息
        Object target = jp.getTarget();
        System.out.println("after：" + target);
        System.out.println("方法：" + jp.getSignature());
        Object[] objects = jp.getArgs();
        for (Object o : objects) {
            System.out.println("参数：" + o);
        }
    }
    @After("update()")      //spring是一个ioc容器，它可以使用 di将程序运行的信息注入
    private void bye( JoinPoint jp) {
        System.out.println("===bye===");
        //连接点的所有的信息
        Object target = jp.getTarget();
        System.out.println("目标类为：" + target);
        System.out.println("方法：" + jp.getSignature());
        Object[] objects = jp.getArgs();
        for (Object o : objects) {
            System.out.println("参数：" + o);
        }
    }
    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
        private Object comput(ProceedingJoinPoint pjp) throws Throwable {
            long start =System.currentTimeMillis();
            System.out.println("======comput1开始");
            Object retVal =pjp.proceed();
            long end =System.currentTimeMillis();
            System.out.println("======comput1结束");
            System.out.println("这个方法运行的时长为："+(end-start));
            return retVal;
        }
    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    private Object aomput2(ProceedingJoinPoint pjp) throws Throwable {
        long start =System.currentTimeMillis();
        System.out.println("======comput2开始");
        Object retVal =pjp.proceed();
        long end =System.currentTimeMillis();
        System.out.println("======comput2结束");
        System.out.println("这个方法运行的时长为："+(end-start));
        return retVal;
    }


}
