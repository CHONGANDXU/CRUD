package com.little.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */

@Data
@Schema(title = "User对象", description = "Login时使用的tb_user表")
public class Users implements UserDetails {
    @Schema(title = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "真实姓名")
    @TableField("name")
    private String name;

    @Schema(title = "用户姓名")
    @TableField("username")
    private String username;

    @Schema(title = "密码")
    @TableField("password")
    @Size(min = 6, max = 16, message = "密码长度必须是6-16个字符")
    private String password;

    @Schema(title = "用户描述")
    private String remark;

    @Schema(title = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @Schema(title = "乐观锁")
    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 返回登陆用户的权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // 返回登录用户账户是否未过期。返回true，未过期，可用。
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    // 返回登录用户账户是否未锁定。返回true，未锁定，可用
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    // 返回登录用户账户凭证是否未过期。返回true，未过期，可用。凭证：密码
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    // 返回登录用户账户是否生效。返回true，账户生效|有效，可用。
    @Override
    public boolean isEnabled() {
        return false;
    }
}
