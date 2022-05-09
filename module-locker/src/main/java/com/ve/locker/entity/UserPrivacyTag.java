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
@TableName("t_user_privacy_tag")
@ApiModel(value="UserPrivacyTag对象", description="")
public class UserPrivacyTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "隐私id")
    @TableField("user_privacy_id")
    private String userPrivacyId;

    @ApiModelProperty(value = "标签id")
    @TableField("privacy_tag_id")
    private String privacyTagId;

    @ApiModelProperty(value = "类型")
    @TableField("privacy_info_type")
    private String privacyInfoType;
}
