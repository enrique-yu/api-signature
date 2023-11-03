package com.icoolkj.api.wrap.core.exception;

/**
 * Replay Attack Exception
 *
 * @author icoolkj
 */
public class WrapReplayAttackException extends WrapException
{
    public WrapReplayAttackException() {
    }

    public WrapReplayAttackException(String message) {
        super(message);
    }

    public WrapReplayAttackException(String message, Throwable cause) {
        super(message, cause);
    }
}
