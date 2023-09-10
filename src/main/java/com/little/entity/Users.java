package com.little.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
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
@Schema(title = "Users对象", description = "Login时使用的users表")
public class Users implements Serializable {

    @Schema(title="ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(title="用户名")
    private String username;

    @Schema(title="密码")
    @TableField("`password`")
    private String password;

    @Schema(title="逻辑删除")
    @TableLogic
    private Integer deleted;

    @Schema(title="乐观锁")
    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
