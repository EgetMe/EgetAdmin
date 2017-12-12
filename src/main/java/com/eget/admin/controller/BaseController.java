package com.eget.admin.controller;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.eget.admin.security.EgetAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author geforce
 * @date 2017/12/12
 */
public class BaseController {

    @ExceptionHandler(EgetAuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleAuthException(EgetAuthException e) {
        return ResponseDataFactory.createFailResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }


}
