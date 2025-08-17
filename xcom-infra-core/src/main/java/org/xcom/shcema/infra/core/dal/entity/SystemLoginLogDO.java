package org.xcom.shcema.infra.core.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xcom.shcema.core.model.EntityBaseDO;

/**
 * 系统登录日志;system_login_log数据表的DO对象
 * @author : xcom
 * @date : 2025-8-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("system_login_log")
public class SystemLoginLogDO extends EntityBaseDO {

    /**
     * 登录ip,;
     */
    private String loginIp;

    /**
     * 备忘录,;
     */
    private String memo;
}