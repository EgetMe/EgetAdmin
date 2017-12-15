package com.eget.admin.common.exception;

/**
 * @author geforce
 * @date 2017/12/13
 */
public class RequestLimitException extends RuntimeException {

    public RequestLimitException() {
        super("请求超出限制的次数");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
