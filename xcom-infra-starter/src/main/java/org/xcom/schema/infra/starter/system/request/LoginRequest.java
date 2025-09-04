package org.xcom.schema.infra.starter.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录请求
 *
 * @author xcom
 * @date 2025/9/4
 */

@Data
public class LoginRequest implements Serializable {

    @ApiModelProperty(value = "账户", required = true)
    @NotBlank(message = "账号不能为空")
    @Length(min = 1, max = 90, message = "账号长度必须是1~90之间")
    private String userName;
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

}
