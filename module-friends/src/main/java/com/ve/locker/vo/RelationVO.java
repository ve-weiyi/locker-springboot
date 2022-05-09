package com.ve.locker.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
@ApiModel(description = "亲密关系标签")
public class RelationVO {

    /**
     * id
     */
    @ApiModelProperty(name = "relationId", value = "关系id", dataType = "Integer")
    private Integer relationId;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    @ApiModelProperty(name = "relationName", value = "关系名", required = true, dataType = "String")
    private String relationName;


    @ApiModelProperty(name = "relationType", value = "关系类型", required = true, dataType = "String")
    private String relationType;
}
