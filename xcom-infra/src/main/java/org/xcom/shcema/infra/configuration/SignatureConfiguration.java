package org.xcom.shcema.infra.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xcom.shcema.infra.interceptor.SignatureInterceptor;


/**
 * 签名 拦截器配置
 *
 * @author xcom
 * @date 2024/8/9
 */

@Configuration
@Order(0)
public class SignatureConfiguration implements WebMvcConfigurer {

    @Resource
    private SignatureInterceptor signatureInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signatureInterceptor)
                .addPathPatterns("/**");
    }

}
