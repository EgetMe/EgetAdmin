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



    /**
     * 创建Token
     * @return
     */
    public String createToken(String username) {
        String compactJws = Jwts.builder()
                .setSubject(username)
                .setExpiration(generateExpired())
                .signWith(SignatureAlgorithm.HS256, key)
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
                Jws<Claims> claims =  Jwts.parser().setSigningKey(key).parseClaimsJws(token);
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
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    public static void main(String[] args) {
        AbstractTokenUtil tokenUtil = new AbstractTokenUtil() {
            @Override
            public String createToken(String usernmae) {
                return super.createToken(usernmae);
            }
        };
        tokenUtil.expiration = 5L;
        tokenUtil.key = "test";
        String token = tokenUtil.createToken("hahahah");

        System.out.println(token);

        String username = tokenUtil.getSafeUserName(token);

        System.out.println(username);

        try {
            Thread.sleep(6000);
            username = tokenUtil.getSafeUserName(token);
            System.out.println(username);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
