package com.icoolkj.api.utils;

import com.icoolkj.api.enums.ResponseMessageCode;

/**
 * 请求返回工具类
 *
 * @author icoolkj
 */
public class ResponseMessageUtils
{
    public static ResponseMessage success() {
        return success(ResponseMessageCode.SUCCESS.getCode(), ResponseMessageCode.SUCCESS.getMessage(), null, ResponseMessageCode.SUCCESS.getDescription());
    }

    public static <T> ResponseMessage<T> success(T t) {
        return success(ResponseMessageCode.SUCCESS.getCode(), ResponseMessageCode.SUCCESS.getMessage(), t, ResponseMessageCode.SUCCESS.getDescription());
    }

    public static <T> ResponseMessage<T> success(int code, String message, T t) {
        return success(code, message, t, "");
    }

    public static <T> ResponseMessage<T> success(int code, String message, T t, String description) {
        return error(code, message, t, description);
    }

    public static ResponseMessage error() {
        return error(ResponseMessageCode.ERROR.getCode(), ResponseMessageCode.ERROR.getMessage(), null, ResponseMessageCode.ERROR.getDescription());
    }

    public static ResponseMessage error(String message) {
        return error(ResponseMessageCode.ERROR.getCode(), message, null, ResponseMessageCode.ERROR.getDescription());
    }

    public static ResponseMessage error(int code, String message) {
        return error(code, message, null, "");
    }

    public static <T> ResponseMessage<T> error(int code, String message, T t, String description) {
        return new ResponseMessage(code, message, t, description);
    }
}
