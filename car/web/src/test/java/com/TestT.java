package com;

import com.leezhong.dao.TestMapper;
import com.leezhong.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestT {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestService testService;
    @Test
    public void test1(){

//        com.leezhong.domain.Test test = testMapper.selectById(1);
//        System.out.println(test);
        com.leezhong.domain.Test tetsById = testService.getTetsById(1);
        System.out.println(tetsById);


    }
}

