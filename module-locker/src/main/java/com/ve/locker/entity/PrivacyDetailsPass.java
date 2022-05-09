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
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_privacy_details_pass")
@ApiModel(value="PrivacyDetailsPass对象", description="")
public class PrivacyDetailsPass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录账号")
    private String account;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "绑定手机号")
    private String phone;

    @ApiModelProperty(value = "所属app名")
    @TableField("app_name")
    private String appName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否加密")
    @TableField("enable_encrypt")
    private Integer enableEncrypt;


}
