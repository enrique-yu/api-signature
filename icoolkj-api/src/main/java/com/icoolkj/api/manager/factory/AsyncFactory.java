package com.icoolkj.api.manager.factory;

import com.icoolkj.api.entity.OpenApiOperLog;
import com.icoolkj.api.service.OpenApiOperLogService;
import com.icoolkj.api.spring.SpringUtils;
import com.icoolkj.api.utils.ip.AddressUtils;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author icoolkj
 */
public class AsyncFactory
{
    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OpenApiOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(OpenApiOperLogService.class).insertOperlog(operLog);
            }
        };
    }
}
