package com.icoolkj.api.wrap.core.exception;

/**
 * 自定义AES加密异常
 *
 * @author: haiwei.yu01
 */
public class AesEncryptionException extends WrapException
{
    public AesEncryptionException() {
    }

    public AesEncryptionException(String message) {
        super(message);
    }

    public AesEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
