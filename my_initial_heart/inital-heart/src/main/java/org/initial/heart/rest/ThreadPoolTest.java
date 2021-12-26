package org.initial.heart.rest;

import org.initial.heart.utils.thread.MyThreadPoolConfig;
import org.omg.CosNaming._BindingIteratorImplBase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

//异步编排 任务1执行完可以执行任务2和3，然后全部完成最终结果
public class ThreadPoolTest {
    @Autowired
    ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    MyThreadPoolConfig myThreadPoolConfig;

    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            //返回任务1操作结果
            return null;
        }, threadPoolExecutor);
        CompletableFuture<Void> voidCompletableFuture = uCompletableFuture.thenAcceptAsync((res) -> {
            //任务2操作
            return ;
        }, threadPoolExecutor);
        CompletableFuture<Void> voidCompletableFuture2 =uCompletableFuture.thenAcceptAsync((res) -> {
            //任务3操作
            return ;
        }, threadPoolExecutor);
        //等待所有任务都完成
        CompletableFuture.allOf(uCompletableFuture, voidCompletableFuture, voidCompletableFuture2).get();

    }
}
