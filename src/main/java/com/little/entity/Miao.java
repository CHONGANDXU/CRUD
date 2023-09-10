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
@Schema(title = "Miao对象", description = "CRUD所使用的miao表")
@Data
public class Miao implements Serializable {

    @Schema(title="编码")
    @TableId(value = "`Code`", type = IdType.AUTO)
    private Integer code;

    @Schema(title="英文翻译")
    private String englishTerm;

    @Schema(title="英文同义词")
    private String synonyms;

    @Schema(title="英文解释/描述")
    private String englishDefinitionDescription;

    @Schema(title="中文")
    private String chineseTerm;

    @Schema(title="拼音")
    private String pinyinTerm;

    @Schema(title="中文同义词")
    private String chineseSynonyms;

    @Schema(title="苗语")
    private String miaoLanguage;

    @Schema(title="来源")
    private String source;

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
