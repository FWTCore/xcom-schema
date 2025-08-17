package org.xcom.schema.quartz.starter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动加载配置文件
 *
 * @author xcom
 * @date 2024/8/8
 */

@Configuration
@ComponentScan("org.xcom.schema.quartz.starter")
@MapperScan("org.xcom.schema.quartz.starter.dal.mapper")
public class AutoConfigure {

}
