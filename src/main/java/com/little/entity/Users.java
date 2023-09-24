package com.little.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */

@Data
@Schema(title = "Users对象", description = "Login时使用的users表")
public class Users implements UserDetails {
    @Schema(title = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "用户名")
    @TableField("username")
    private String username;

    @Schema(title = "密码")
    @TableField("password")
    @Size(min = 6, max = 16, message = "密码长度必须是6-16个字符")
    private String password;

    @Schema(title = "启用与否")
    @TableField("enabled")
    private Boolean enabled;

    // 此参数在表中并不存在
    @Schema(title = "权限")
    @TableField(exist = false)
    private List<GrantedAuthority> authorityList;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
