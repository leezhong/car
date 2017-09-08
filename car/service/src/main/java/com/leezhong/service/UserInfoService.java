package com.leezhong.service;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    void saveJsonMap2UserInfo(List<Map<String,String>> jsonMaps);

    /**
     *
     * @param data yyyy-MM-dd
     */
    void getUserInfoBySpider(String data);

}
