package com.icoolkj.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池属性类
 *
 * @author: haiwei.yu01
 **/
@Component
@ConfigurationProperties(prefix = "thread.poolexecutor")
public class ThreadPoolProperties
{
    // 核心线程池大小
    private Integer corePoolSize;

    // 最大可创建的线程数
    private Integer maxPoolSize;

    // 队列最大长度
    private Integer queueCapacity;

    // 线程池维护线程所允许的空闲时间
    private Integer keepAliveSeconds;

    // 线程名称前缀
    private String prefixName;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public Integer getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }
}
