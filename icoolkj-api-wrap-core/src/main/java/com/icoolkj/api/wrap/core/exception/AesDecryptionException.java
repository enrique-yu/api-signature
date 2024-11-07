package com.icoolkj.api.wrap.core.exception;

/**
 * 自定义AES解密异常
 *
 * @author: haiwei.yu01
 */
public class AesDecryptionException extends WrapException
{
    public AesDecryptionException() {
    }

    public AesDecryptionException(String message) {
        super(message);
    }

    public AesDecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
