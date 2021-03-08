package com.moses.cloud.commons.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author HanKeQi
 * @Date 2021/2/2 下午6:24
 * @Version 1.0
 **/
@Configuration
@EnableAsync
public class ThreadConfig extends AsyncConfigurerSupport {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor() {
        // 线程池定义
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(7);
        threadPoolTaskExecutor.setMaxPoolSize(42);
        threadPoolTaskExecutor.setQueueCapacity(11);
        threadPoolTaskExecutor.setThreadNamePrefix("MyExecutor-");
        threadPoolTaskExecutor.initialize();
        // sleuth 中延时跟踪执行
        return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }
}
