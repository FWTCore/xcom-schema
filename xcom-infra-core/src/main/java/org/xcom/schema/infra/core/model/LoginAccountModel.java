package org.xcom.schema.infra.core.model;

import lombok.Data;
import org.xcom.schema.infra.core.enums.SystemMenuEnum;
import org.xcom.schema.infra.core.enums.SystemRoleEnum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录账户 数据模型
 *
 * @author xcom
 * @date 2025/8/17
 */

public class LoginAccountModel {

    /**
     * 登录账户模型
     */
    @Data
    public static class LoginUserRespBO implements Serializable {

        private static final long serialVersionUID = -7059884802225152123L;

        /**
         * 用户id
         */
        private Long              id;

        /**
         * 公司id
         */
        private Long              companyId;

        /**
         * 公司
         */
        private String            companyName;

        /**
         * 登录名称
         */
        private String            loginName;
        /**
         * 用户名称
         */
        private String            displayName;
        /**
         * 本次登录时间
         */
        private LocalDateTime     lastLoginTime;

    }

    /**
     * 登录用户权限
     */
    @Data
    public static class LoginUserPermissionsRespDO implements Serializable {

        private static final long         serialVersionUID = -7059884802225152123L;

        /**
         * 用户id
         */
        private Long                      userId;

        /**
         * 公司id
         */
        private Long                      companyId;

        /**
         * 是否是超管
         */
        private Boolean                   superAdminFlag;

        /**
         * 本次登录时间
         */
        private LocalDateTime             lastLoginTime;

        /**
         * 用户角色
         */
        private List<LoginUserRoleRespBO> loginUserRoleList;

        /**
         * 用户授权
         */
        private List<LoginUserAuthRespBO> loginUserAuthList;

    }

    /**
     * 用户角色
     */
    @Data
    public static class LoginUserRoleRespBO implements Serializable {

        private static final long serialVersionUID = -7059884802225152123L;

        /**
         * 角色id
         */
        private Long              roleId;
        /**
         * 角色名称
         */
        private String            roleName;
        /**
         * 角色类型
         *
         * @see SystemRoleEnum.RoleTypeEnum
         */
        private Short             roleType;

    }

    /**
     * 用户权限
     */
    @Data
    public static class LoginUserAuthRespBO implements Serializable {
        private static final long serialVersionUID = -7059884802225152123L;

        /**
         * 菜单id
         */
        private Long              menuId;

        /**
         * 父id,;
         */
        private Long              parentId;

        /**
         * 菜单类型,;
         * @see SystemMenuEnum.MenuTypeEnum
         */
        private Short             menuType;

        /**
         * 页面名称,;
         */
        private String            pageName;

        /**
         * 页面url,;
         */
        private String            urlPath;

        /**
         * 组件名称,;
         */
        private String            componentName;

        /**
         * 组件,;
         */
        private String            component;

        /**
         * 菜单名称,;
         */
        private String            menuName;

        /**
         * 菜单图标,;
         */
        private String            iconStyle;

        /**
         * 是否开启缓存,;
         */
        private Boolean           keepAlive;

        /**
         * 是否在菜单中隐藏,;
         */
        private Boolean           hideInMenu;

        /**
         * 是否在标签页中隐藏,;
         */
        private Boolean           hideInTab;

        /**
         * 是否在面包屑中隐藏,;
         */
        private Boolean           hideInBreadcrumb;

        /**
         * 页面的子页面是否在菜单中隐藏,;
         */
        private Boolean           hideChildrenInMenu;

        /**
         * 页面徽标,;
         */
        private String            badge;

        /**
         * 徽标类型;dot | normal,dot 为小红点，normal 为文本，默认值：normal,;
         */
        private String            badgeType;

        /**
         * 徽标颜色;default | destructive | primary | success | warning | string，默认值：success,;
         */
        private String            badgeVariants;

        /**
         * 当前激活的菜单,;
         */
        private String            activeUrlPath;

        /**
         * 是否固定标签页,;
         */
        private Boolean           affixTab;

        /**
         * 页面固定标签页的排序;采用升序排序,;
         */
        private Short             affixTabOrder;

        /**
         * 内嵌页面的 iframe 地址,;
         */
        private String            iframeSrc;

        /**
         * 是否忽略权限,;
         */
        private Boolean           ignoreAccess;

        /**
         * 外链跳转路径,;
         */
        private String            link;

        /**
         * 菜单可见不能打开,;
         */
        private Boolean           menuVisibleWithForbidden;

        /**
         * 是否在新窗口打开页面,;
         */
        private Boolean           openInNewWindow;

        /**
         * 页面的排序,;
         */
        private Short             sortBy;

        /**
         * 页面的菜单参数,;
         */
        private String            query;

        /**
         * 授权key,;
         */
        private String            authKey;

        /**
         * 是否发布;线上灰度验证,;
         */
        private Boolean           releaseFlag;

    }

}
