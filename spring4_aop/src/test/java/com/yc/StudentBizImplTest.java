package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyAppConfig.class)
public class StudentBizImplTest extends TestCase {
    @Resource(name = "studentBizImpl")
    private StudentBiz sbi;
    @Test
    public void testadd(){
        sbi.add("DSds");
    }
    @Test
    public void testupdate(){
        sbi.update("DSds");
    }

    @Test
    public void testfind(){
        sbi.find("DSds");
    }


}


