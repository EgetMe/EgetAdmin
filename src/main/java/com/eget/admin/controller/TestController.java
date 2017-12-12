package com.eget.admin.controller;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.eget.admin.dao.UserMapper;
import com.eget.admin.pojo.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geforce
 * @date 2017/12/7
 */
@RestController
@Api(tags = "测试")
public class TestController {



    @GetMapping("hello")
    @ApiOperation("hello")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",required = true,paramType = "header",
                    dataType = "string",value = "token header",defaultValue = "EGET ")
    })
    public ResponseData hello(){

        return ResponseDataFactory.createSuccessResponse();
    }


}
