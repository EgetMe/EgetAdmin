package com.eget.admin.common.annotation;

import com.eget.admin.common.exception.RequestLimitException;
import com.eget.admin.common.utils.WebHelper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author geforce
 * @date 2017/12/13
 */
@Aspect
@Component
public class RequestLimitContract {
    private final Logger logger = LoggerFactory.getLogger(getClass());



    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void requestPointCut() {

    }


    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit) ")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;
        for (Object obj :
                args) {
            if (obj instanceof HttpServletRequest) {
                request = (HttpServletRequest) obj;
                break;
            }
        }

        if (request == null) {
            throw new RequestLimitException("方法中缺失HttpServletRequest参数");
        }

        String ip = WebHelper.getRemoteAddr(request);
//        logger.info(ip);
        String url = request.getRequestURL().toString();

        String key = url;

        long count = redisTemplate.opsForValue().increment(key,1);

        if (count == 1) {
            redisTemplate.expire(key,limit.time(),TimeUnit.SECONDS);
        }

        if (count>limit.value()) {
            throw new RequestLimitException();
        }
    }

}