package com.little.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author chengchong
 */
@Data
@Schema(title = "Role对象", description = "CRUD所使用的who表")
public class Role {
    @Schema(title = "Role_ID")
    @TableField("id")
    private Integer id;

    @Schema(title = "角色名称")
    @TableField("name")
    private String name;

    @Schema(title = "角色描述")
    @TableField("remark")
    private String remark;

}
