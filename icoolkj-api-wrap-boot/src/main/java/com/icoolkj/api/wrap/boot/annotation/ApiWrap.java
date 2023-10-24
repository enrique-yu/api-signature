package com.icoolkj.api.wrap.boot.annotation;

import com.icoolkj.api.wrap.boot.WrapHandler;
import com.icoolkj.api.wrap.boot.handler.WrapHandlerServer;

import java.lang.annotation.*;

/**
 * ApiWrap 接口签名注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiWrap {
    Class<? extends WrapHandler> value() default WrapHandlerServer.class;
}
