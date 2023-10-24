package com.icoolkj.api.wrap.boot.annotation;

import com.icoolkj.api.wrap.boot.ApiWrapEnablerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 允许API签名
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiWrapEnablerConfiguration.class})
public @interface EnableApiWrap {
}
