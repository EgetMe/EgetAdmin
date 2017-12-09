package com.eget.admin.dao;

import com.eget.admin.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author geforce
 * @date 2017/12/7
 */
@Mapper
public interface UserMapper {

    int insert(User user);

    User selectById(Integer id);

    User selectByUsername(String username);
}
