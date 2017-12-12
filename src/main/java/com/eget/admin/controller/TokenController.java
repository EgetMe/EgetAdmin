package com.eget.admin.controller;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.eget.admin.pojo.User;
import com.eget.admin.security.TokenFactory;
import com.eget.admin.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geforce
 * @date 2017/12/8
 */
@RestController
@RequestMapping("token")
@Api(tags = "权限管理")
public class TokenController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenFactory tokenFactory;

    @PostMapping
    @ApiOperation("获取Token")
    private ResponseData getToken(
            @ApiParam(required = true,value = "用户名")  @RequestParam() String username,
            @ApiParam(required = true,value = "密码") @RequestParam() String password){





        User user = userService.getUser(username,password);
        if (user != null) {
            Map<String,String> result = new HashMap<>();
            result.put("token",tokenFactory.getEgetTokenUtil().createToken(username));
            return ResponseDataFactory.createSuccessResponse("OK",result);
        }

        return ResponseDataFactory.createFailResponse();

    }

}
