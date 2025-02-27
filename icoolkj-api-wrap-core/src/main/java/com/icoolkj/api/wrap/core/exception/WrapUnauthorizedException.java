package com.icoolkj.api.wrap.core.exception;

/**
 * 未授权异常
 *
 * @author: haiwei.yu01
 */
public class WrapUnauthorizedException extends WrapException
{
    public WrapUnauthorizedException() {
    }

    public WrapUnauthorizedException(String message) {
        super(message);
    }

    public WrapUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
