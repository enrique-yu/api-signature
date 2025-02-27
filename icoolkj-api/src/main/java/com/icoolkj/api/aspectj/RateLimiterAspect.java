package com.icoolkj.api.aspectj;

import cn.hutool.core.util.ObjectUtil;
import com.icoolkj.api.annotation.RateLimiter;
import com.icoolkj.api.enums.LimitType;
import com.icoolkj.api.utils.RequestContextHolderUtil;
import com.icoolkj.api.utils.ip.IpUtils;
import com.icoolkj.api.wrap.core.WrapData;
import com.icoolkj.api.wrap.core.WrapRequest;
import com.icoolkj.api.wrap.core.exception.WrapRateLimiterException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 限流处理
 *
 * @author: haiwei.yu01
 */
@Aspect
@Component
public class RateLimiterAspect
{
    private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    private RedisTemplate<Object, Object> redisTemplate;

    private RedisScript<Long> limitScript;

    @Autowired
    public void setRedisTemplate1(RedisTemplate<Object, Object> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setLimitScript(RedisScript<Long> limitScript)
    {
        this.limitScript = limitScript;
    }

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable
    {
        int time = rateLimiter.time();
        int count = rateLimiter.count();

        Object[] args = point.getArgs();
        String combineKey = null;
        for (Object obj : args) {
            if (obj instanceof WrapRequest) {
                WrapRequest<WrapData> request = (WrapRequest<WrapData>) obj;
                combineKey = getCombineKey(rateLimiter, point, request);
            }
        }
        List<Object> keys = Collections.singletonList(combineKey);
        try
        {
            Long number = redisTemplate.execute(limitScript, keys, count, time);
            if (ObjectUtil.isNull(number) || number.intValue() > count)
            {
                throw new WrapRateLimiterException("请求过于频繁，请稍候再试");
            }
            log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), combineKey);
        }
        catch (WrapRateLimiterException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new WrapRateLimiterException("服务器限流异常，请稍候再试");
        }
    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point, WrapRequest<WrapData> request)
    {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP)
        {
            stringBuffer.append(IpUtils.getIpAddr(RequestContextHolderUtil.getRequest())).append("-");
        } else if(rateLimiter.limitType() == LimitType.APP_KEY){
            stringBuffer.append(request.getAppKey()).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
}
