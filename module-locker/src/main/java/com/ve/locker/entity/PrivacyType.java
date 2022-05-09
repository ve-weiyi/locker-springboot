package com.ve.locker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiyi
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_privacy_type")
@ApiModel(value="PrivacyType对象", description="")
public class PrivacyType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty(value = "英文名称")
    @TableField("type_name_en")
    private String typeNameEn;

    @ApiModelProperty(value = "图标")
    @TableField("type_cover")
    private String typeCover;

    @ApiModelProperty(value = "颜色")
    @TableField("type_color")
    private String typeColor;

    @ApiModelProperty(value = "描述")
    @TableField("type_desc")
    private String typeDesc;

    @ApiModelProperty(value = "创建者id")
    @TableField("owner_id")
    private Integer ownerId;

}
