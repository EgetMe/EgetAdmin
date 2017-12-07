package com.eget.admin.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geforce
 * @date 2017/12/7
 */
@RestController
public class TestController {
    @GetMapping("hello")
    public String hello(){
        return "Hello";
    }


}
