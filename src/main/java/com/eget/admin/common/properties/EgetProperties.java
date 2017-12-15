package com.eget.admin.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author geforce
 * @date 2017/12/11
 */
@ConfigurationProperties(prefix = "eget")
public class EgetProperties {
    private TokenProperties token = new TokenProperties();

    public TokenProperties getToken() {
        return token;
    }

    public void setToken(TokenProperties token) {
        this.token = token;
    }


}
