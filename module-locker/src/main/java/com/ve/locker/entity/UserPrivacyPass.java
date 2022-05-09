package com.ve.locker.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_privacy_pass")
@ApiModel(value="UserPrivacyPass对象", description="")
public class UserPrivacyPass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "隐私id")
    @TableField("privacy_details_id")
    private Integer privacyDetailsId;

    @ApiModelProperty(value = "隐私名")
    @TableField("privacy_name")
    private String privacyName;

    @ApiModelProperty(value = "隐私图标")
    @TableField("privacy_cover")
    private String privacyCover;

    @ApiModelProperty(value = "隐私描述")
    @TableField("privacy_desc")
    private String privacyDesc;

    @ApiModelProperty(value = "类型id")
    @TableField("privacy_type_id")
    private Integer privacyTypeId;

    @ApiModelProperty(value = "文件夹id")
    @TableField("privacy_folder_id")
    private Integer privacyFolderId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "版本号")
    private String version;


}
