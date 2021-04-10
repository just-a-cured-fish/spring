package com.yc.biz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.yc.dao.StudentDao;

@Service//给spring的类托管
public class StudentBizImpl implements StudentBiz {
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {
    }

    @Autowired
    @Qualifier("studentDaoJpaImpl")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int add(String name) {
        System.out.println(" -------业务层------------");
        System.out.println("用户名是否重名");
        int result = studentDao.add(name);
        System.out.println("业务操作结束");
        return result;
    }

    @Override
    public void update(String name) {
        System.out.println("=====业务层=====");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("业务层结束");
    }
    @Override
    public void find(String name) {
        System.out.println("=====业务层=====");
        System.out.println("用户名是否重名");
        studentDao.find(name);
        System.out.println("业务层结束");
    }
}
