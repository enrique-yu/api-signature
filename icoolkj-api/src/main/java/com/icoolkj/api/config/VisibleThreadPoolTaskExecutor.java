package com.icoolkj.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor
{
    private static final Logger log = LoggerFactory.getLogger(VisibleThreadPoolTaskExecutor.class);

    private void log(String method){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if(threadPoolExecutor==null){
            return;
        }

        log.info("线程池：{}, 执行方法：{},任务数量 [{}], 完成任务数量 [{}], 活跃线程数 [{}], 队列长度 [{}]",
                this.getThreadNamePrefix(),
                method,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        log("execute");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        log("execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        log("submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        log("submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        log("submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        log("submitListenable");
        return super.submitListenable(task);
    }
}
