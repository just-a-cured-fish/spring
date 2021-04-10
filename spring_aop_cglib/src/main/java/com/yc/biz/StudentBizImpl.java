package com.yc.biz;

public class StudentBizImpl implements StudentBiz {

    @Override
    public int add(String name) {
        System.out.println("执行了add方法"+name);
        return 0;
    }

    @Override
    public void update(String name) {
        System.out.println("执行了update方法");
    }

    @Override
    public void find(String name) {
        System.out.println("执行了find方法"+name);
    }
}
