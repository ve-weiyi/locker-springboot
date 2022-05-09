package com.ve.locker.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色
 *
 * @author yezhiqiu
 * @date 2021/08/03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "好友亲密关系")
public class RelationshipVO {
    @ApiModelProperty(name = "friendId", value = "好友id", dataType = "Integer")
    private Integer friendId;

    @ApiModelProperty(name = "relationIds", value = "关系id列表", required = true, dataType = "String")
    private List<Integer> relationIds;
}
