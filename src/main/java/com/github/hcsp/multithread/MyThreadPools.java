package com.github.hcsp.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;

public class MyThreadPools {
    // 创建一个线程池，满足以下要求：
    // 同时最多有10个任务在执行
    // 任务等待队列大小为20，如果超过20继续往该线程池中提交任务，这些任务会被悄悄丢弃
    // 线程的名字为"MyThread"
    public static ExecutorService myThreadPool() {
        int corePoolSize = 10;
        int maximumPoolSize = 10;
        long keepAliveTime = 60;
        TimeUnit unit = TimeUnit.SECONDS;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                r -> new Thread(r, "MyThread"),
                handler);
        return threadPoolExecutor;
    }
}
