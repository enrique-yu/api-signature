package com.icoolkj.api.exception;

import com.icoolkj.api.enums.ResponseMessageCode;
import com.icoolkj.api.utils.ResponseMessage;
import com.icoolkj.api.utils.ResponseMessageUtils;
import com.icoolkj.api.wrap.core.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

/**
 * API 统一异常处理类
 *
 * @author icoolkj
 */
@RestControllerAdvice
public class ApiGlobalException
{
    private static final Logger log = LoggerFactory.getLogger(ApiGlobalException.class);

    /**
     * 系统内部错误
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseMessage RuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',系统内部错误'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.ERROR.getCode(),
                ResponseMessageCode.ERROR.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * bean对象属性验证
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseMessage ConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',请求参数错误'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.BAD_REQUEST.getCode(),
                ResponseMessageCode.BAD_REQUEST.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 未授权
     */
    @ExceptionHandler(WrapUnauthorizedException.class)
    public ResponseMessage WrapUnauthorizedException(WrapUnauthorizedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',未授权'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.UNAUTHORIZED.getCode(),
                ResponseMessageCode.UNAUTHORIZED.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 签名错误
     */
    @ExceptionHandler(InvalidWrapSignatureException.class)
    public ResponseMessage InvalidWrapSignatureException(InvalidWrapSignatureException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',签名错误'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.INVALID_WRAP_SIGNATURE_ERROR.getCode(),
                ResponseMessageCode.INVALID_WRAP_SIGNATURE_ERROR.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 时间戳异常
     */
    @ExceptionHandler(WrapTimestampException.class)
    public ResponseMessage WrapTimestampException(WrapTimestampException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',时间戳异常'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.TIMESTAMP_ERROR.getCode(),
                ResponseMessageCode.TIMESTAMP_ERROR.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 限流异常
     */
    @ExceptionHandler(WrapRateLimiterException.class)
    public ResponseMessage WrapRateLimiterException(WrapRateLimiterException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',限流异常'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.WRAP_RATE_LIMITER.getCode(),
                ResponseMessageCode.WRAP_RATE_LIMITER.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 重复请求
     */
    @ExceptionHandler(WrapReplayAttackException.class)
    public ResponseMessage WrapReplayAttackException(WrapReplayAttackException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',重复请求'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.WRAP_REPLAY_ATTACK_ERROR.getCode(),
                ResponseMessageCode.WRAP_REPLAY_ATTACK_ERROR.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 签名异常
     */
    @ExceptionHandler(WrapSigningException.class)
    public ResponseMessage WrapSigningException(WrapSigningException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.WRAP_SIGNING_ERROR.getCode(),
                ResponseMessageCode.WRAP_SIGNING_ERROR.getMessage(),
                null,
                e.getMessage());
    }

    /**
     * 验证异常
     */
    @ExceptionHandler(WrapVerifierException.class)
    public ResponseMessage WrapVerifierException(WrapVerifierException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',验证异常'{}'", requestURI, e.getMessage());
        return ResponseMessageUtils.error(ResponseMessageCode.WRAP_VERIFIER_ERROR.getCode(),
                ResponseMessageCode.WRAP_VERIFIER_ERROR.getMessage(),
                null,
                e.getMessage());
    }

}
