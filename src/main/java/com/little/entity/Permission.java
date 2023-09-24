package com.little.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author chengchong
 */
@Data
public class Permission {
    @Schema(title = "权限ID")
    @TableField("id")
    private Integer id;

    @Schema(title = "权限名称")
    @TableField("name")
    private String name;

    @Schema(title = "请求地址")
    @TableField("url")
    private String url;

    @Schema(title = "父权限主键")
    @TableField("parent_id")
    private Integer parentId;

    @Schema(title = "权限类型")
    @TableField("type")
    private String type;

    @Schema(title = "权限字符串描述")
    @TableField("permit")
    private String permit;

    @Schema(title = "描述")
    @TableField("remark")
    private String remark;

}
