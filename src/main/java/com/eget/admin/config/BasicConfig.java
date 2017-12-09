package com.eget.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author geforce
 * @date 2017/12/7
 */
@Configuration
@MapperScan("com.eget.admin.dao")
public class BasicConfig {

}
