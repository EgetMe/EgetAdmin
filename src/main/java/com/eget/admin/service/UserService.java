package com.eget.admin.service;

import com.eget.admin.pojo.User;

import java.util.List;

/**
 * @author geforce
 * @date 2017/12/7
 */
public interface UserService {


    User getUser(String username,String password);

    User getUser(String username);

    User getUser(Long id);

    Integer saveUser(String username,String password);

    List<User> getAllUsers();
}
