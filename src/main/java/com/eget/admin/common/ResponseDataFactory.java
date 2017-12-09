package com.eget.admin.common;

import org.springframework.http.HttpStatus;

/**
 * @author geforce
 * @date 2017/12/7
 */
public class ResponseDataFactory {


    public static ResponseData createSuccessResponse() {
        return createSuccessResponse("OK");
    }

    public static ResponseData createSuccessResponse(String msg) {
        return new ResponseData(HttpStatus.OK.value(), msg, null);
    }

    public static ResponseData createSuccessResponse(String msg, Object obj) {
        return new ResponseData(HttpStatus.OK.value(), msg, obj);
    }

    public static ResponseData createFailResponse() {
        return createFailResponse(HttpStatus.BAD_REQUEST.value());
    }

    public static ResponseData createFailResponse(int code) {
        return createFailResponse(code, "error");
    }

    public static ResponseData createFailResponse(int code, String msg) {
        return new ResponseData(code, msg, null);
    }

}
