package com.yc.biz;

import com.yc.dao.StudentDao;
import com.yc.springframework.stereotype.MyRepository;

import java.util.Random;

@MyRepository
public class StudentDaoJpaImpl implements StudentDao {
    @Override
    public int add(String name) {
        System.out.println("jpa添加学生：" + name);
        Random r = new Random();
        return r.nextInt();
    }

    @Override
    public void update(String name) {
        System.out.println("jpa更新学生：" + name);
    }
}
