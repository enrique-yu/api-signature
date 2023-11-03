package com.icoolkj.api.wrap.core.exception;

/**
 * Base Wrap Exception
 *
 * @author icoolkj
 */
public class WrapException extends RuntimeException
{
    public WrapException() {
    }

    public WrapException(String message) {
        super(message);
    }

    public WrapException(String message, Throwable cause) {
        super(message, cause);
    }
}
