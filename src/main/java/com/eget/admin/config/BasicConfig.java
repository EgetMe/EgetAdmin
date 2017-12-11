package com.eget.admin.config;

import com.eget.admin.common.properties.EgetProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author geforce
 * @date 2017/12/7
 */
@Configuration
@MapperScan("com.eget.admin.dao")
@EnableConfigurationProperties(EgetProperties.class)
public class BasicConfig {

}
