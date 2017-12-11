package com.eget.admin.common.properties;

/**
 * @author geforce
 * @date 2017/12/11
 */
public class TokenProperties {
    private String key;
    private Long expiration;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
