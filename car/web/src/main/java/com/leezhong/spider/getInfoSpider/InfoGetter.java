package com.leezhong.spider.getInfoSpider;

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
@ContextConfiguration("classpath:applicationContext.xml")
public class InfoGetter {

    private static String key = "";

    private String url = "";

    private String date = "2017-09-05";

    private Integer pageSize = 20;

    private Integer pageNo = 1;

    @Autowired
    private UserInfoService userInfoService;


    @Test
   public void getUserInfo(){

        String sign = MD5Util.MD5(date + "|" + pageNo + "|" + key).toLowerCase();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("sign",sign);
        param.put("pageSize",pageSize);
        param.put("date",date);
        param.put("pageNo",pageNo);
        System.out.println(param);
        try {
            //第一次获取当日数据的信息
            String res = HttpClientUtilsWithPool.post(url, param);
            if(res!=null&&!"".equals(res)){
                res = res.trim();
                Map<String,Object> map = JacksonUtil.JsonToMap(res);
                Integer totalSize = Integer.valueOf(map.get("totalSize").toString());
                Integer size = Integer.valueOf(map.get("size").toString());//本次条数
                if(size<=0){
                    System.out.println("无数据");
                    return;
                }
                //根据获取信息进行数据抓取
                double floor = Math.ceil(totalSize / Double.valueOf(pageSize + ""));
                int countPage = new BigDecimal(floor).intValue();
                ExecutorService executors = Executors.newFixedThreadPool(countPage);
                CountDownLatch countDownLatch = new CountDownLatch(countPage);

                try {
                    String thisSign = null;
                    for (int i = 1; i <= countPage ; i++) {
                        thisSign = MD5Util.MD5(date + "|" + i + "|" + key).toLowerCase();
                        String url_ = url+"?sign="+thisSign+"&pageSize="+pageSize+"&date="+date+"&pageNo="+i;
                        HttpGet httpget = new HttpGet();
                        HttpClientUtilsWithPool.config(httpget);
                        // 启动线程抓取
                        executors.execute(new GetRunnable(url_,countDownLatch,userInfoService));
                    }
                    countDownLatch.await();
                    executors.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }finally {
                    System.out.println("-----抓取结束----");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
