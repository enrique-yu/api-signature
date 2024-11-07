package com.icoolkj.api.wrap.core.exception;

/**
 * 自定义AES密钥生成异常
 *
 * @author: haiwei.yu01
 */
public class AesKeyGenerationException extends WrapException
{
    public AesKeyGenerationException() {
    }

    public AesKeyGenerationException(String message) {
        super(message);
    }

    public AesKeyGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
