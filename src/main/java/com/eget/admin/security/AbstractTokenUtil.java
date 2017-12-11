package com.eget.admin.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.StringUtils;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

/**
 * @author geforce
 * @date 2017/12/8
 */
public abstract class AbstractTokenUtil {

    private static final String TOKEN_TYPE = "EGET ";

    private Long expiration;

    private String key;

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 创建Token
     * @return
     */
    public String createToken(String username) {
        String compactJws = Jwts.builder()
                .setSubject(username)
                .setExpiration(generateExpired())
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
        return TOKEN_TYPE + compactJws;
    }


    public String getUsername(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }


    public String getSafeUserName(String token) {
        if (!isTokenExpired(token)){
            return getUsername(token);
        }
        return null;
    }

    private Date getExpiredDate(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            return claims.getExpiration();
        }
        return null;
    }


    private Claims parseToken(String token) {
        token = getJwtToken(token);
        if (StringUtils.isNotBlank(token)) {
            try {
                Jws<Claims> claims =  Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
                Claims c =  claims.getBody();
                return c;
            }catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private String getJwtToken(String token) {
        if (isSupportToken(token)){
            return token.substring(TOKEN_TYPE.length());
        }
        return null;
    }

    public boolean isSupportToken(String token) {
        if (StringUtils.startsWith(token,TOKEN_TYPE)){
            return true;
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        Date date = getExpiredDate(token);
        if (date != null) {
            return date.before(new Date());
        }
        return true;
    }

    /**
     * 计算过期时间
     *
     * @return Date
     */
    private Date generateExpired() {
        return new Date(System.currentTimeMillis() + getExpiration() * 1000);
    }




}
