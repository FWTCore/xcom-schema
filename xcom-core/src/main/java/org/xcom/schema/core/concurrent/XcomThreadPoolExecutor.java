package org.xcom.schema.core.concurrent;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.MDC;
import org.xcom.schema.core.constant.SystemConstant;
import org.xcom.schema.core.context.AccessContextHolder;
import org.xcom.schema.core.context.AccessUser;
import org.xcom.schema.core.context.XcomThreadContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomThreadPoolExecutor extends ThreadPoolExecutor {


    public XcomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public XcomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public XcomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public XcomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void afterExecute(Runnable runnable, Throwable throwable) {
        super.afterExecute(runnable, throwable);
        clearCurrentThreadContext();
    }

    @Override
    public void execute(Runnable runnable) {
        XcomThreadContext xcomThreadContext = getCurrentThreadContext();
        super.execute(() -> {
            setCurrentThreadContext(xcomThreadContext);
            runnable.run();
        });
    }


    /**
     * 设置当前线程上下文
     *
     * @param xcomThreadContext
     */
    private void setCurrentThreadContext(XcomThreadContext xcomThreadContext) {
        MDC.put(SystemConstant.SYSTEM_LOG_TRACE_ID, xcomThreadContext.getTraceLogId());
        MDC.put(SystemConstant.SYSTEM_LOG_USER_ID, xcomThreadContext.getTraceUserId());
        AccessUser accessUser = xcomThreadContext.getAccessUser();
        if (ObjectUtils.isNotEmpty(accessUser)) {
            AccessContextHolder.bind(accessUser);
        }

    }

    /**
     * 清空当前线程上下文
     */
    private void clearCurrentThreadContext() {
        MDC.clear();
        AccessContextHolder.clear();
    }

    /**
     * 获取当前线程上下文
     *
     * @return
     */
    private XcomThreadContext getCurrentThreadContext() {
        XcomThreadContext xcomThreadContext = new XcomThreadContext();
        xcomThreadContext.setTraceLogId(MDC.get(SystemConstant.SYSTEM_LOG_TRACE_ID));
        xcomThreadContext.setTraceUserId(MDC.get(SystemConstant.SYSTEM_LOG_USER_ID));
        xcomThreadContext.setAccessUser(AccessContextHolder.getAccessUser().orElseGet(AccessUser::new));
        return xcomThreadContext;
    }


}
