package com.eget.admin.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geforce
 * @date 2017/12/14
 */
public class WebHelper {

    private static Logger logger = LoggerFactory.getLogger(WebHelper.class);


    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        logger.info("X-Real-IP:" + remoteAddr);
        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            logger.info("X-Forwarded-For:" + remoteAddr);
        }

        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
            logger.info("Proxy-Client-IP:" + remoteAddr);
        }

        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
            logger.info("WL-Proxy-Client-IP:" + remoteAddr);
        }

        String ip = remoteAddr != null ? remoteAddr :request.getRemoteAddr();
        ip = ip.replace("0:0:0:0:0:0:0:1","127.0.0.1");
        logger.info(ip);
        return ip;
    }


}
