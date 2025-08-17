package org.xcom.schema.core.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 线程异常处理器
 * 线程池中的线程或者子线程，异常无法在主线程中记录
 * 为了避免手动try  catch ，使用异常处理器进行兜底处理
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(XcomThreadExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("线程：" + t.getName() + "执行任务时出现异常", e);
    }
}
