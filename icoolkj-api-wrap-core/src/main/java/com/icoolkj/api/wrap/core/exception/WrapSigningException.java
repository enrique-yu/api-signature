package com.icoolkj.api.wrap.core.exception;

/**
 * 签名异常
 *
 * @author icoolkj
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
