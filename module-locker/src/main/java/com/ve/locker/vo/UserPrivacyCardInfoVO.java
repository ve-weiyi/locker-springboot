package com.ve.locker.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ve.locker.entity.PrivacyDetailsCard;
import com.ve.locker.entity.PrivacyFolder;
import com.ve.locker.entity.PrivacyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色
 * vo 后端返回给前端的数据类型
 * @author yezhiqiu
 * @date 2021/08/03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "用户隐私信息")
public class UserPrivacyCardInfoVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "隐私名")
    @TableField("privacy_name")
    private String privacyName;

    @ApiModelProperty(value = "隐私图标")
    @TableField("privacy_cover")
    private String privacyCover;

    @ApiModelProperty(value = "隐私描述")
    @TableField("privacy_desc")
    private String privacyDesc;


    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "版本号")
    private String version;


    @ApiModelProperty(value = "类型id")
    @TableField("privacy_type_id")
    private Integer privacyTypeId;

    @ApiModelProperty(value = "文件夹id")
    @TableField("privacy_folder_id")
    private Integer privacyFolderId;

    @ApiModelProperty(value = "隐私id")
    @TableField("privacy_details_id")
    private Integer privacyDetailsId;



    @ApiModelProperty(value = "隐私类型")
    private PrivacyType privacyType;

    @ApiModelProperty(value = "隐私文件夹")
    private PrivacyFolder privacyFolder;

    @ApiModelProperty(value = "隐私详细信息")
    private PrivacyDetailsCard privacyDetails;


    @ApiModelProperty(value = "隐私标签")
    private List<PrivacyTagVO> privacyTags;
}
