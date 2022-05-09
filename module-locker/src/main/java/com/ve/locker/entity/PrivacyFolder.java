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
@TableName("t_privacy_folder")
@ApiModel(value="PrivacyFolder对象", description="")
public class PrivacyFolder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件夹名称")
    @TableField("folder_name")
    private String folderName;

    @ApiModelProperty(value = "图标")
    @TableField("folder_cover")
    private String folderCover;

    @ApiModelProperty(value = "描述")
    @TableField("folder_desc")
    private String folderDesc;

    @ApiModelProperty(value = "创建者id")
    @TableField("owner_id")
    private Integer ownerId;


}
