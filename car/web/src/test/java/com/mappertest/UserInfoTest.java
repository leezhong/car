package com.mappertest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leezhong.dao.UserInfoMapper;
import com.leezhong.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserInfoTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testSelect(){

        PageHelper.startPage(1,30);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo(userInfos);

        System.out.println(pageInfo);
    }
    @Test
    public void testSelectByProvince(){

        List<UserInfo> list = userInfoMapper.selectByAddr("北");
        System.out.println(list);
    }


}
