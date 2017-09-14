package com.leezhong.service.impl;

import com.leezhong.dao.UserInfoMapper;
import com.leezhong.domain.UserInfo;
import com.leezhong.service.UserInfoService;
import com.leezhong.staticDt.CommonData;
import com.leezhong.thread.GetRunnable;
import com.leezhong.utils.HttpClientUtilsWithPool;
import com.leezhong.utils.JacksonUtil;
import com.leezhong.utils.MD5Util;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public void saveJsonMap2UserInfo(List<Map<String, String>> jsonMaps) {

        if (jsonMaps == null || jsonMaps.size() == 0) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = null;
        for (Map<String, String> map : jsonMaps) {
            try {
                userInfo = new UserInfo();
                userInfo.setAddr(map.get("addr"));
                userInfo.setOtherid(map.get("id") == null ? null : Integer.valueOf(map.get("id")));
                userInfo.setName(map.get("name"));
                userInfo.setAge(map.get("age"));
                userInfo.setMobi(map.get("mobi"));
                userInfo.setMoney(map.get("money"));
                userInfo.setDays(map.get("days"));
                userInfo.setInterest(map.get("interest"));
                userInfo.setProvinceNo(map.get("provinceNo"));
                userInfo.setZmxy(map.get("zmxy"));
                userInfo.setQq(map.get("qq"));
                userInfo.setCreaeteTime(map.get("createtime") == null ? null : sdf.parse(map.get("createtime")));
                userInfo.setWechat(map.get("wechat"));
                userInfo.setRepayType(map.get("repaytype"));
                userInfoMapper.insert(userInfo);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public void getUserInfoBySpider(String date) {

        if(date==null||"".equals(date.trim())){
            return;
        }
        if(!date.contains("-")){
            return;
        }
        String key = CommonData.getUserInfoKey;
        String url = CommonData.getUserInfoUrl;
        Integer pageSize = 20;
        String sign = MD5Util.MD5(date + "|" + 1 + "|" + key).toLowerCase();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("sign", sign);
        param.put("pageSize", pageSize);
        param.put("date", date);
        param.put("pageNo", 1);
        System.out.println(param);
        try {
            //第一次获取当日数据的信息
            String res = HttpClientUtilsWithPool.post(url, param);
            if (res != null && !"".equals(res)) {
                res = res.trim();
                Map<String, Object> map = JacksonUtil.JsonToMap(res);
                Integer totalSize = Integer.valueOf(map.get("totalSize").toString());
                Integer size = Integer.valueOf(map.get("size").toString());//本次条数
                if (size <= 0) {
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
                    for (int i = 1; i <= countPage; i++) {
                        thisSign = MD5Util.MD5(date + "|" + i + "|" + key).toLowerCase();
                        String url_ = url + "?sign=" + thisSign + "&pageSize=" + pageSize + "&date=" + date + "&pageNo=" + i;
                        HttpGet httpget = new HttpGet();
                        HttpClientUtilsWithPool.config(httpget);
                        // 启动线程抓取
                        executors.execute(new GetRunnable(url_, countDownLatch, this));
                    }
                    countDownLatch.await();
                    executors.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("-----抓取结束----");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
