package com.icoolkj.api.service;

import com.icoolkj.api.entity.OpenApiOperLog;
import com.icoolkj.api.mapper.OpenApiOperLogMapper;
import com.icoolkj.api.utils.uuid.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志 服务层处理
 *
 * @author: haiwei.yu01
 */
@Service
public class OpenApiOperLogService
{
    @Autowired
    private OpenApiOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OpenApiOperLog operLog)
    {
        operLog.setOperId(IdWorker.nextId().toString());
        operLogMapper.insertOperlog(operLog);
    }

}
