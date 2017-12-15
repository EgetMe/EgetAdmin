package com.eget.admin.controller;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.eget.admin.common.annotation.RequestLimit;
import com.eget.admin.dao.UserMapper;
import com.eget.admin.pojo.User;
import com.eget.admin.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geforce
 * @date 2017/12/7
 */
@RestController
@Api(tags = "测试")
@RequestMapping("test")
public class TestController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestLimit(value = 2,time = 10)
    @GetMapping("hello")
    @ApiOperation("hello")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",required = true,paramType = "header",
                    dataType = "string",value = "token header",defaultValue = "EGET ")
    })
    public ResponseData hello(HttpServletRequest request){

        return ResponseDataFactory.createSuccessResponse();
    }


    @GetMapping("save")
    @ApiOperation("保存用户测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",required = true,paramType = "header",
            dataType = "string",value = "token header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseData save(HttpServletRequest request){

        int[] arr = {0,0,0,0,0,0};
        for (int i = 0; i < 6; i++) {
            System.out.println(arr[i]);
            int random = RandomUtils.nextInt();
            String uname = "test"+random;
            userService.saveUser(uname,"sss");
        }

        return  ResponseDataFactory.createSuccessResponse();
    }

    @GetMapping("getAllUser")
    @ApiOperation("测试获取所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "token 值",name = "token",dataType = "string",paramType = "header")
    })
    public ResponseData getAllUsers() {
        return ResponseDataFactory.createSuccessResponse("ok",userService.getAllUsers());
    }


}



