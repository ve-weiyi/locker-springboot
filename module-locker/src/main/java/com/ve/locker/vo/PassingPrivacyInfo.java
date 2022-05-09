package com.ve.locker.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description hello word!
 * @Author weiyi
 * @Date 2022/3/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "解析隐私信息")
public class PassingPrivacyInfo {

    @ApiModelProperty(value = "隐私内容",notes =" 请注意！该对象中的所有String字段都会被加密解析！ " ,example = "{   }",position = 1)
    private Object privacyObject;

    @NotBlank
    @ApiModelProperty(value = "秘钥串",notes =" 请使用正确的秘钥串 " ,example = "",position = 3)
    private String keyString;

    @ApiModelProperty(value = "秘钥类型",notes ="秘钥类型.0 AES秘钥 | 1 RSA私钥| 2 RSA 公钥",position = 4)
    private Integer keyType;

    @ApiModelProperty(value = "AES向量",notes ="如果秘钥类型为AES可填。必须小于16位",position = 5)
    private Integer iv;

    @ApiModelProperty(value = "加密模式",notes = "加密模式.0 解密 | 1加密",position = 6)
    private Integer model;

}
