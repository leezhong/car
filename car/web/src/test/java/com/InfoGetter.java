package com;

import com.leezhong.service.UserInfoService;
import com.leezhong.utils.HttpClientUtilsWithPool;
import com.leezhong.utils.JacksonUtil;
import com.leezhong.utils.MD5Util;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class InfoGetter {


    @Autowired
    private UserInfoService userInfoService;


    @Test
   public void getUserInfo(){

        userInfoService.getUserInfoBySpider("2017-09-08");

    }



}
