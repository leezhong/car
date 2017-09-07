package com.leezhong.service.impl;

import com.leezhong.dao.UserInfoMapper;
import com.leezhong.domain.UserInfo;
import com.leezhong.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserInfoServiceImpl  implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    public void saveJsonMap2UserInfo(List<Map<String,String>> jsonMaps) {

        if(jsonMaps==null||jsonMaps.size()==0){
            return ;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = null;
        for(Map<String,String> map:jsonMaps){
            try {
                userInfo = new UserInfo();
                userInfo.setAddr(map.get("addr"));
                userInfo.setOtherid(map.get("id")==null?null:Integer.valueOf(map.get("id")));
                userInfo.setName(map.get("name"));
                userInfo.setAge(map.get("age"));
                userInfo.setMobi(map.get("mobi"));
                userInfo.setMoney(map.get("money"));
                userInfo.setDays(map.get("days"));
                userInfo.setInterest(map.get("interest"));
                userInfo.setProvinceNo(map.get("provinceNo"));
                userInfo.setZmxy(map.get("zmxy"));
                userInfo.setQq(map.get("qq"));
                userInfo.setCreaeteTime(map.get("createtime")==null?null:sdf.parse(map.get("createtime")));
                userInfo.setWechat(map.get("wechat"));
                userInfo.setRepayType(map.get("repaytype"));
                userInfoMapper.insert(userInfo);
            }catch (ParseException e){
                e.printStackTrace();
            }

        }
    }
}
