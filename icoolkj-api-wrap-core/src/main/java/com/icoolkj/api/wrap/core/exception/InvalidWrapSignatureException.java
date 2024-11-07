package com.icoolkj.api.wrap.core.exception;

/**
 * 签名错误
 *
 * @author: haiwei.yu01
 */
public class InvalidWrapSignatureException extends WrapException
{
    public InvalidWrapSignatureException() {
    }

    public InvalidWrapSignatureException(String message) {
        super(message);
    }

    public InvalidWrapSignatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
