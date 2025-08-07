package org.xcom.shcema.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 访问上下文信息封装内
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
public class AccessContextHolder {

    private static final ThreadLocal<AccessUser> ACCESS_CONTEXT = new TransmittableThreadLocal<>();


    /**
     * 绑定当前访问用户
     *
     * @param accessUser 访问用户
     */
    public static void bind(AccessUser accessUser) {
        ACCESS_CONTEXT.set(accessUser);
        log.debug("Access context 绑定成功: {}", ACCESS_CONTEXT.get());
    }

    /**
     * 清除绑定对象
     */
    public static void clear() {
        ACCESS_CONTEXT.remove();
    }

    /**
     * 获取当前上下文的访问用户
     *
     * @return 上下文用户
     */
    public static Optional<AccessUser> getAccessUser() {
        return Optional.ofNullable(ACCESS_CONTEXT.get());
    }

}
