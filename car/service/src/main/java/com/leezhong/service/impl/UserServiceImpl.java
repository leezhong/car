package com.leezhong.service.impl;

import com.leezhong.dao.UserMapper;
import com.leezhong.domain.User;
import com.leezhong.service.UserService;
import com.leezhong.shiro.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<String> getUserRoles(int userId) {
        return null;
    }

    @Override
    public List<String> getUserStringPermissions(int userId) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int createUser(User user) {
        user.setCreateTime(new Date());
        PasswordHelper.encryptPassword(user);
        return userMapper.insertSelective(user);
    }
}
