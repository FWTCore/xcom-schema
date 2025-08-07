package org.xcom.shcema.infra.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xcom.shcema.infra.interceptor.UserAuthInterceptor;

/**
 * 用户权限拦截器
 *
 * @author xcom
 * @date 2024/8/11
 */

@Configuration
@Order(20)
public class UserAuthWebConfigurer implements WebMvcConfigurer {

    @Resource
    private UserAuthInterceptor userAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthInterceptor)
                .addPathPatterns("/**");
    }
}