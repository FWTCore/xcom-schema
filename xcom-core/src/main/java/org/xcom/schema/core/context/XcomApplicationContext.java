package org.xcom.schema.core.context;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Xcom 应用
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomApplicationContext implements ApplicationContextAware {

    /**
     * 应用信息
     */
    public static ApplicationContext applicationContext;

    /**
     * 获取运行环境
     *
     * @return
     */
    public static String getFirstActiveProfiles() {
        if (ObjectUtils.isEmpty(applicationContext)) {
            return null;
        }
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    /**
     * 获取应用id
     *
     * @return
     */
    public static String getApplicationId() {
        return applicationContext.getId();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        XcomApplicationContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }
}
