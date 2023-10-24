package com.icoolkj.api.mapper;


import com.icoolkj.api.entity.OpenApiOperLog;

public interface OpenApiOperLogMapper
{
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OpenApiOperLog operLog);

}
