package com.icoolkj.api.wrap.core.exception;

/**
 * 签名异常
 *
 * @author: haiwei.yu01
 */
public class WrapSigningException extends WrapException
{
    public WrapSigningException() {
    }

    public WrapSigningException(String message) {
        super(message);
    }

    public WrapSigningException(String message, Throwable cause) {
        super(message, cause);
    }
}
