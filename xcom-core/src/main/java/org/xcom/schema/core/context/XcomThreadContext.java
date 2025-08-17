package org.xcom.schema.core.context;

import lombok.Data;

/**
 * 线程上下文
 *
 * @author xcom
 * @date 2024/8/9
 */

@Data
public class XcomThreadContext<T extends AccessUser> {

    private String traceLogId;

    private String traceUserId;

    private T accessUser;

}

