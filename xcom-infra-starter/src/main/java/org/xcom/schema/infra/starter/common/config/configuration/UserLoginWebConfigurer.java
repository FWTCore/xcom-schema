package org.xcom.schema.infra.starter.common.config.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xcom.schema.infra.starter.common.config.interceptor.UserLoginInterceptor;

/**
 * 用户登录拦截器
 *
 * @author xcom
 * @date 2024/8/11
 */

@Configuration
@Order(10)
public class UserLoginWebConfigurer implements WebMvcConfigurer {

    @Resource
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**");
    }

}