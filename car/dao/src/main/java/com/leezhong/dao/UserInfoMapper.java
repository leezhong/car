package com.leezhong.dao;

import com.github.pagehelper.PageInfo;
import com.leezhong.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectAll();

    List<UserInfo> selectByAddr(String addr);

    List<UserInfo> selectByAge(Integer age);

    List<UserInfo> findByName(@Param("name") String name);

}