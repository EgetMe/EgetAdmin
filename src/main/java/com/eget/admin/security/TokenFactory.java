package com.eget.admin.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author geforce
 * @date 2017/12/11
 */
@Service
public class TokenFactory {

    @Autowired
    private AbstractTokenUtil egetTokenUtil;


    public AbstractTokenUtil getEgetTokenUtil() {
        return egetTokenUtil;
    }
}
