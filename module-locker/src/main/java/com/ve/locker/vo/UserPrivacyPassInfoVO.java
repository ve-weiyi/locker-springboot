package com.ve.locker.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ve.locker.entity.PrivacyDetailsPass;
import com.ve.locker.entity.PrivacyFolder;
import com.ve.locker.entity.PrivacyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
public class UserPrivacyPassInfoVO {

    @ApiModelProperty(value = "id",position = 1 )
    private Integer id;

    @ApiModelProperty(value = "用户id",position = 2)
    private Integer userId;

    @NotNull(message = "隐私名不能为空")
    @ApiModelProperty(value = "隐私名",notes = "标签的备注名",example = "XX的QQ邮箱账号",position = 4)
    private String privacyName;

    @ApiModelProperty(value = "隐私图标",notes = "标签的覆盖图标",example ="https://ve77.cn/blog/favicon.ico",position = 5)
    private String privacyCover;

    @ApiModelProperty(value = "隐私描述",notes = "标签描述",example = "床前明月光",position = 6)
    private String privacyDesc;

    @ApiModelProperty(value = "创建时间",notes = "标签创建时间,不用填",position = 7)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间",notes = "标签更新时间,不用填",position = 8)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "版本号",notes = "标签更新版本号,不用填",position = 9)
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
    private PrivacyDetailsPass privacyDetails;


    @ApiModelProperty(value = "隐私标签")
    private List<PrivacyTagVO> privacyTags;
}
