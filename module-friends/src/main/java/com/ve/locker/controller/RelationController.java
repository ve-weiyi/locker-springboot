package com.ve.locker.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.ve.locker.annotation.OptLog;
import com.ve.locker.constant.OptTypeConst;
import com.ve.locker.entity.Relation;
import com.ve.locker.service.IRelationService;
import com.ve.locker.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 关系控制器
 *
 * @author weiyi
 * @since 2022-01-14
 */
@Api(tags = "好友关系模块")
@ApiSupport(order = 36)
@RestController
public class RelationController {
    @Autowired
    private IRelationService relationsService;
    
    /**
     * 查询关系列表
     *
     */
    @ApiOperation(value = "查询关系列表")
    @GetMapping("/admin/relations/list")
    public Result<?> listRelations() {
        return Result.ok(relationsService.list());
    }

    /**
     * 增加或更新关系
     *
     * @param relation 关系信息
     * @return {@link Result<>}
     */
    @OptLog(optType = OptTypeConst.SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新关系")
    @PostMapping("/admin/relations/update")
    public Result<?> saveOrUpdateRole(@RequestBody @Valid Relation relation) {
        if(relation.getRelationName().equals("")){
            return Result.fail("数据错误！");
        }
        relationsService.saveOrUpdate(relation);
        return Result.ok();
    }

    /**
     * 删除关系
     *
     * @param relationsIdList 关系id列表
     * @return {@link Result<>}
     */
    @OptLog(optType = OptTypeConst.REMOVE)
    @ApiOperation(value = "删除关系")
    @DeleteMapping("/admin/relations/delete")
    public Result<?> deleteRelations(@RequestBody List<Integer> relationsIdList) {
        if(relationsIdList.isEmpty()){
            return Result.fail("数据错误！");
        }
        relationsService.removeByIds(relationsIdList);
        return Result.ok();
    }

}
