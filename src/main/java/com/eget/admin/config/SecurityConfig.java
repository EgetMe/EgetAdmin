package com.eget.admin.config;

import com.eget.admin.security.EgetAuthenticationEnterPoint;
import com.eget.admin.security.EgetSecurityTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author geforce
 * @date 2017/12/7
 */
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler egetAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler egetAuthenticationFailHandler;

    @Autowired
    private EgetAuthenticationEnterPoint egetAuthenticationEnterPoint;



    @Bean
    public EgetSecurityTokenFilter egetSecurityTokenFilter() {
        return new EgetSecurityTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/token").permitAll();
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(egetAuthenticationEnterPoint).and()
                .authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(egetSecurityTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
