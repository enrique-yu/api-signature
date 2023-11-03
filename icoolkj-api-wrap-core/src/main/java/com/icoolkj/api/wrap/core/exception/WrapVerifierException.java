package com.icoolkj.api.wrap.core.exception;

/**
 * 验证异常
 *
 * @author icoolkj
 */
public class WrapVerifierException extends WrapException
{
    public WrapVerifierException() {
    }

    public WrapVerifierException(String message) {
        super(message);
    }

    public WrapVerifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
