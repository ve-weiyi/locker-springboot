package com.ve.locker.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ve.locker.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description hello word!
 * @Author weiyi
 * @Date 2022/1/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel( description="好友信息")
public class FriendVo {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "备注名")
    private String remarkName;

    @ApiModelProperty(name = "user_info", value = "详细信息", required = true, dataType = "UserInfo")
    private UserInfo userInfo;

    @ApiModelProperty(value = "亲密关系")
    private List<RelationVO> relations;

    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)//告诉mybatis plus 数据库中没有这个自定义的字段
    private List<RoleTagVO> roles;
}
