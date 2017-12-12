package com.eget.admin.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author geforce
 * @date 2017/12/12
 */
public class EgetAuthException extends AuthenticationException {

    public EgetAuthException(String msg) {
        super(msg);
    }
}
