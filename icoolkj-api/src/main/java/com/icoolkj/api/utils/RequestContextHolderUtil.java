package com.icoolkj.api.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 应用级对象获取工具类
 *
 * @author: haiwei.yu01
 */
public class RequestContextHolderUtil
{
    public static String getHeader(String headerName){
        return getRequest().getHeader(headerName);
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader("user-agent")).orElse(StrUtil.EMPTY);
    }

    public static String getRemoteAddr() {
        HttpServletRequest request = getRequest();
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StrUtil.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (StrUtil.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (StrUtil.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return Optional.ofNullable(remoteAddr).orElse(request.getRemoteAddr());
    }

    public static String requestUrl() {
        return Optional.ofNullable(getRequest().getRequestURI()).orElse(StrUtil.EMPTY);
    }

    public static String requestMethod() {
        return Optional.ofNullable(getRequest().getMethod()).orElse(StrUtil.EMPTY);
    }

    public static Map<String, String[]> getParams(ServletRequest request)
    {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getParamMap(ServletRequest request)
    {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet())
        {
            params.put(entry.getKey(), ArrayUtil.join(entry.getValue(), ","));
        }
        return params;
    }

}
