package org.xcom.shcema.infra.starter.config.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.xcom.shcema.core.context.XcomApplicationContext;
import org.xcom.shcema.core.model.InfraProperties;
import org.xcom.shcema.core.redis.XcomRedisTemplate;
import org.xcom.shcema.infra.starter.config.introspect.DataDictIntrospect;

/**
 * 全局配置
 *
 * @author xcom
 * @date 2024/8/8
 */

@Configuration
@Order(1)
public class StarterGlobalConfiguration {

    @Resource
    private DataDictIntrospect dictSensitiveAnnotationIntrospect;


    /**
     * 全局统一配置跨域
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //是否允许请求带有验证信息
        config.setAllowCredentials(true);
        // 允许访问的客户端域名
        config.addAllowedOriginPattern("*");
        // 允许服务端访问的客户端请求头
        config.addAllowedHeader("*");
        // 允许访问的方法名,GET POST等
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.annotationIntrospector(dictSensitiveAnnotationIntrospect);
    }

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        //防全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        // region 数据权限插件
        //DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
        // 添加自定义的数据权限处理器
        //dataPermissionInterceptor.setDataPermissionHandler(new XcomDataPermissionHandler());
        //interceptor.addInnerInterceptor(dataPermissionInterceptor);
        // endregion

        // region 分页插件 如果配置多个插件,切记分页最后添加
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 数据库类型mysql，如果有多数据源可以不配具体类型 否则都建议配上具体的DbType
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 溢出总页数的时候默认是跳到第一页
        paginationInnerInterceptor.setOverflow(true);
        // 设置最大分页数，导出每次查询最多1000条
        paginationInnerInterceptor.setMaxLimit(1000L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        // endregion

        return interceptor;
    }

    // region base Bean

    /**
     * 初始化应用上下文
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "applicationContext")
    public XcomApplicationContext applicationContext() {
        return new XcomApplicationContext();
    }

    @Bean
    @ConditionalOnMissingBean(name = "infraProperties")
    public InfraProperties infraProperties() {
        return new InfraProperties();
    }

    /**
     * 设置Redis序列化方式，默认使用的JDKSerializer的序列化方式，效率低，这里我们使用 Jackson2JsonRedisSerializer
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @Order(0)
    @ConditionalOnMissingBean(name = "xcomRedisTemplate")
    public RedisTemplate<String, Object> xcomRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //解决jackson 无法反序列化LocalDateTime的问题
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // key序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // Hash key序列化
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // value序列化
        redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        XcomRedisTemplate.initInstance(redisTemplate);
        return redisTemplate;
    }

    // endregion


}
