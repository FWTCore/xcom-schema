package org.xcom.schema.core.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.xcom.schema.core.exception.XcomException;
import org.xcom.schema.core.enums.SystemCodeEnum;

import java.util.concurrent.CompletableFuture;

/**
 * 异步功能 工具类
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
public class CompletableFutureUtil {

    public static <T> T getCompletableFutureResult(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (Exception exception) {
            log.error("CompletableFuture 获取结果异常:", exception);
            throw XcomException.create(SystemCodeEnum.BUSINESS_EXCEPTION);
        }

    }
}
