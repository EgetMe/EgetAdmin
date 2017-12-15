package com.eget.admin.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author geforce
 * @date 2017/12/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {

    /**
     *  允许的次数，默认是MAX_VALUE
     * @return
     */
    int value() default Integer.MAX_VALUE;

    /**
     * 时间限制，默认是60秒
     * @return
     */
    int time() default 60;

}
