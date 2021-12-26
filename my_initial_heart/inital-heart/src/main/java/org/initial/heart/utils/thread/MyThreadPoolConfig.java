package org.initial.heart.utils.thread;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
//如何属性类没有使用@Component注解，解决方案
//@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class MyThreadPoolConfig {
    //抛弃策略
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(20, 50,
                10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
    @Bean("threadPoolExecutor2")
    public ThreadPoolExecutor threadPoolExecutor2(ThreadPoolConfigProperties   pool) {
        return new ThreadPoolExecutor(pool.getCoreSize(),
                pool.getMaxSize(),
                pool.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }


}
