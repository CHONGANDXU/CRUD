package com.little.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Authorities {
    @Schema(title = "用户名")
    @TableField("username")
    private String username;

    @Schema(title = "密码")
    @TableField("authority")
    private String authority;


}
