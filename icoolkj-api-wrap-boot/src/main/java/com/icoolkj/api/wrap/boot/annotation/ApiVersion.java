package com.icoolkj.api.wrap.boot.annotation;

import java.lang.annotation.*;

/**
 * ApiVersion 接口版本注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion
{
    /**
     * 标识版本号，从1开始
     */
    int value() default 1;
}
