package org.xcom.shcema.infra.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动加载配置文件
 *
 * @author xcom
 * @date 2025/2/21
 */

@Configuration
@ComponentScan("org.xcom.shcema.infra.core")
@MapperScan("org.xcom.shcema.infra.core.dal.mapper")
public class AutoConfigure {
}
