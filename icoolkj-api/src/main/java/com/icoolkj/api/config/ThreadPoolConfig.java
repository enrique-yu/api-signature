package com.icoolkj.api.config;

import com.icoolkj.api.config.properties.ThreadPoolProperties;
import com.icoolkj.api.utils.Threads;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author: haiwei.yu01
 **/
@Configuration
public class ThreadPoolConfig
{
    private final ThreadPoolProperties threadPoolProperties;

    public ThreadPoolConfig(ThreadPoolProperties threadPoolProperties) {
        this.threadPoolProperties = threadPoolProperties;
    }

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return configureExecutor(new ThreadPoolTaskExecutor());
    }

    @Bean("visibleTaskExecutor")
    public ThreadPoolTaskExecutor visibleThreadPoolTaskExecutor() {
        return configureExecutor(new VisibleThreadPoolTaskExecutor());
    }

    private ThreadPoolTaskExecutor configureExecutor(ThreadPoolTaskExecutor executor) {
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix(threadPoolProperties.getPrefixName());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService()
    {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(threadPoolProperties.getCorePoolSize()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };

        executor.setThreadFactory(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName(threadPoolProperties.getPrefixName() + thread.getId());
            thread.setDaemon(true);
            return thread;
        });

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

}
