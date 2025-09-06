package org.xcom.schema.infra.starter.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "登录请求")
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    @Schema(description = "账户", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空")
    @Length(min = 1, max = 90, message = "账号长度必须是1~90之间")
    private String            userName;
    @Schema(description = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String            password;

}
