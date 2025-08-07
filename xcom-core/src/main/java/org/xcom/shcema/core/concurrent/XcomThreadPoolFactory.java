package org.xcom.shcema.core.concurrent;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomThreadPoolFactory {

    /**
     * cpu 核数
     */
    private static final int CPU_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * IO线程数
     */
    private static final int IO_POOL_SIZE = CPU_POOL_SIZE < 10 ? 20 : CPU_POOL_SIZE * 2;

    /**
     * 队列等待数
     */
    private static final int QUEUE_SIZE = 400;


    /**
     * io 密集型线程池，适用于io操作频繁的多线程场景
     */
    public static final ExecutorService IO_THEAD_POOL = new XcomThreadPoolExecutor(IO_POOL_SIZE, IO_POOL_SIZE, 0L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE),
            new ThreadFactoryBuilder().setNameFormat("io-pool-%d").setUncaughtExceptionHandler(new XcomThreadExceptionHandler()).build());

    /**
     * cpu 密集型线程池，适用于高并发且耗时短的多线程场景
     */

    public static final ExecutorService CPU_THEAD_POOL = new XcomThreadPoolExecutor(CPU_POOL_SIZE, CPU_POOL_SIZE, 0L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE),
            new ThreadFactoryBuilder().setNameFormat("cpu-pool-%d").setUncaughtExceptionHandler(new XcomThreadExceptionHandler()).build()
    );

    /**
     * 执行Job线程池
     */
    public static final ExecutorService JOB_THEAD_POOL = new XcomThreadPoolExecutor(IO_POOL_SIZE, IO_POOL_SIZE, 0L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE * 2),
            new ThreadFactoryBuilder().setNameFormat("job-pool-%d").setUncaughtExceptionHandler(new XcomThreadExceptionHandler()).build()
    );
}

