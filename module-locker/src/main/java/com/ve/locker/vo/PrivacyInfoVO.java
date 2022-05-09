package com.ve.locker.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiyi
 * @since 2022-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_privacy_info")
@ApiModel(value="PrivacyInfo对象", description="")
public class PrivacyInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",position = 1)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录账号",notes ="登录账号",example = "791422171@qq.com",position = 2)
    private String account;

    @ApiModelProperty(value = "登录密码",notes ="登录密码",example = "123456",position = 3)
    private String password;

    @ApiModelProperty(value = "编号",notes ="证件类型可填证件号，普通账号可不填",example = "U201814550",position = 4)
    private String number;

    @ApiModelProperty(value = "绑定手机号",notes = "可填绑定手机号，可不填",example = "15623356029",position = 5)
    private String phone;

    @ApiModelProperty(value = "绑定地址",notes = "可不填",example = "湖北省 武汉市 华中科技大学 沁苑东十一舍",position = 6)
    private String address;

    @ApiModelProperty(value = "登录页面",notes = "可不填",example = "https://www.hust.edu.cn/",position = 7)
    private String url;

    @ApiModelProperty(value = "签发时间",notes = "证件类型可填签发时间，可不填",example = "2022-2-22",position =8 )
    @TableField("sign_date")
    private String signDate;

    @ApiModelProperty(value = "有效时间",notes = "可不填",example = "2022-2-22",position = 9)
    @TableField("valid_date")
    private String validDate;

    @ApiModelProperty(value = "备注",notes = "其他备注信息",example = "这是一个非常隐秘的账户(＾Ｕ＾)ノ~ＹＯ，不要告诉别人哦",position = 10)
    private String remark;

    @NotBlank
    @ApiModelProperty(value = "是否开启加密",notes = "0:明文 1：密文。开启加密模式后在数据库中是密文存储。",position = 10)
    private Integer enable_encrypt;
}
