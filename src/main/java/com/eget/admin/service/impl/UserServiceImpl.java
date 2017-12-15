package com.eget.admin.service.impl;

import com.eget.admin.dao.UserMapper;
import com.eget.admin.pojo.User;
import com.eget.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geforce
 * @date 2017/12/7
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUser(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && StringUtils.isNotBlank(password) && StringUtils.equals(password,user.getPassword())) {
            return user;
        }
        return null;
    }


    @Override
    public User getUser(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User getUser(Long id) {
        return null;
    }


    @Override
    public Integer saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.insert(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUser();
    }
}
