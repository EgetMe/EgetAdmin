package com.eget.admin.security;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author geforce
 * @date 2017/12/13
 */
@Component
public class EgetAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof EgetAuthException) {
            ResponseData responseData = ResponseDataFactory.createFailResponse(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(responseData));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
