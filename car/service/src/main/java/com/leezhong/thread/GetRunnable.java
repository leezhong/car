package com.leezhong.thread;

import com.leezhong.service.UserInfoService;
import com.leezhong.utils.HttpClientUtilsWithPool;
import com.leezhong.utils.JacksonUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class GetRunnable implements Runnable {
    private CountDownLatch countDownLatch;
    private String url;
    private UserInfoService userInfoService;

    public GetRunnable(String url, CountDownLatch countDownLatch,UserInfoService userInfoService) {
        this.url = url;
        this.countDownLatch = countDownLatch;
        this.userInfoService = userInfoService;
    }

    public void run() {
        try {
            String res = HttpClientUtilsWithPool.get(url);
            Map<String,Object> map = JacksonUtil.JsonToMap(res);
            List<Map<String,String>> data = (List<Map<String,String>>)map.get("data");
            userInfoService.saveJsonMap2UserInfo(data);
        } finally {
            countDownLatch.countDown();
        }
    }
}
