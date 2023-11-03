package com.icoolkj.api.wrap.core.exception;

/**
 * 限流异常
 *
 * @author icoolkj
 */
public class WrapRateLimiterException extends WrapException
{
    public WrapRateLimiterException() {
    }

    public WrapRateLimiterException(String message) {
        super(message);
    }

    public WrapRateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
