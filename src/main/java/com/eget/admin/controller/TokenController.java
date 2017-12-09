package com.eget.admin.controller;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.eget.admin.pojo.User;
import com.eget.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geforce
 * @date 2017/12/8
 */
@RestController
@RequestMapping("token")
public class TokenController {

    @Autowired
    private UserService userService;


    @PostMapping
    private ResponseData getToken(String username,String password){
        User user = userService.getUser(username,password);
        return ResponseDataFactory.createSuccessResponse("OK",user);
    }

}
