package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

public class Test {
    public static  void main(String[] args){
        StudentBiz target=new StudentBizImpl();
        LogAspect la=new LogAspect(target);
        Object obj=la.createProxy();
        StudentBiz sb=(StudentBiz)obj;
        sb.add("张三");
        System.out.println(obj);
    }
}
