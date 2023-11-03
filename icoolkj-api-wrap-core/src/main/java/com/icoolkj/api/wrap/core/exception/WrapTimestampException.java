package com.icoolkj.api.wrap.core.exception;

/**
 * 时间戳异常
 *
 * @author icoolkj
 */
public class WrapTimestampException extends WrapException
{
    public WrapTimestampException() {
    }

    public WrapTimestampException(String message) {
        super(message);
    }

    public WrapTimestampException(String message, Throwable cause) {
        super(message, cause);
    }
}
