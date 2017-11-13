package com.leezhong.service;

import com.leezhong.domain.User;

import java.util.List;

public interface UserService {

    public User getUserInfoById(int userId);

    public List<String> getUserRoles(int userId);

    public List<String> getUserStringPermissions(int userId);

    public User getUserByUsername(String username);

    public int createUser(User user);
}
