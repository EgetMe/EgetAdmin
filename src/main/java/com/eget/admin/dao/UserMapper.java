package com.eget.admin.dao;

import com.eget.admin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author geforce
 * @date 2017/12/7
 */
@Mapper
public interface UserMapper {

    int insert(User user);

    User selectById(Integer id);

    User selectByUsername(String username);


    @Select("SELECT * FROM Users")
    List<User> getAllUser();

}
