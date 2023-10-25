package com.icoolkj.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author icoolkj
 */
public class ResponseMessage<T> implements Serializable
{
    private static final Logger logger = LoggerFactory.getLogger(ResponseMessage.class);

    /** 状态码 */
    private int code;

    /** 返回概括信息 */
    private String message;

    /** 数据对象 */
    private T data;

    /** 详细描述 */
    private String description;

    public ResponseMessage() {
    }

    public ResponseMessage(int code, String message, T data, String description) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.description = description;
        logger.info(toString());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
