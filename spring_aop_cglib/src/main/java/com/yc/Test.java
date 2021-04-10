package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

public class Test {
    public static  void main(String[] args){
        StudentBiz sbi=new StudentBizImpl();
        LogAspect lc=new LogAspect(sbi);
        Object obj=lc.createProxy();
        if(obj instanceof StudentBizImpl){
            StudentBizImpl s=(StudentBizImpl)obj;
            s.find("张三");
            s.update("李四");
            s.add("王五");
        }

    }
}
