package com.eget.admin.controller;

import com.eget.admin.common.ResponseData;
import com.eget.admin.common.ResponseDataFactory;
import com.eget.admin.common.exception.RequestLimitException;
import com.eget.admin.security.EgetAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(RequestLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleRequestLimitException(RequestLimitException e) {
        return ResponseDataFactory.createFailResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleMissParam(MissingServletRequestParameterException e){
        return ResponseDataFactory.createFailResponse(HttpStatus.BAD_REQUEST.value(),"缺少必要的参数");
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData handleException(Exception e){
        return ResponseDataFactory.createFailResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }


}
