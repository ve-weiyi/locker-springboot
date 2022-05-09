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
@TableName("t_privacy_details_card")
@ApiModel(value="PrivacyDetailsCard对象", description="")
public class PrivacyDetailsCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "拥有人")
    private String owner;

    @ApiModelProperty(value = "卡号")
    private String number;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "绑定手机号")
    private String phone;

    @ApiModelProperty(value = "绑定地址")
    private String address;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "签发时间")
    @TableField("sign_date")
    private String signDate;

    @ApiModelProperty(value = "有效时间")
    @TableField("valid_date")
    private String validDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否加密")
    @TableField("enable_encrypt")
    private Integer enableEncrypt;


}
