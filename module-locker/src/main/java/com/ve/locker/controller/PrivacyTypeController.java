package com.ve.locker.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.PrivacyType;
import com.ve.locker.service.IPrivacyTypeService;
import com.ve.locker.util.UserUtils;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * 一般的数据传递是，前端传递VO给接口(Controller)，
 * 接口将VO转为DTO传递给service，service将DTO分解为DO，调用领域服务进行调度，
 * 然后逆向转为VO或者其他的返回结果，传递给前台。
 *
 * @author weiyi
 * @since 2022-03-07
 */
@RestController
@RequestMapping("/admin/privacy/type")
public class PrivacyTypeController {

    @Autowired
    private IPrivacyTypeService privacyTypeService;

    /**
     * 添加隐私类型 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加隐私类型",notes = "对传输的数据不做修改存储")
    @PostMapping("/add/json")
    public Result<?> addPrivacyType(@RequestBody @Valid PrivacyType privacyType) {
        privacyType.setOwnerId(UserUtils.getUserId());
        boolean status=privacyTypeService.save(privacyType);
        if(status){
            return Result.ok("操作成功! param"+privacyType);
        }else{
            return Result.fail("操作失败! param"+privacyType);
        }
    }

    /**
     * 删除隐私类型 删
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除隐私类型")
    @DeleteMapping("/delete/json")
    public Result<?> deletePrivacyType(@Valid Integer id) {

        PrivacyType privacyType=privacyTypeService.getById(id);
        if(!privacyType.getOwnerId().equals(UserUtils.getUserId())){
            return Result.fail(String.format("权限不足，操作失败! user id is %s.",UserUtils.getUserId()));
        }

        boolean status=privacyTypeService.removeById(id);
        if(status){
            return Result.ok("删除成功！ param:"+id);
        }else{
            return Result.fail("操作失败! param:"+id);
        }
    }


    /**
     * 更新隐私类型 改
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "更新隐私类型")
    @PostMapping("/update/json")
    public Result<?> updatePrivacyType(@RequestBody @Valid PrivacyType privacyType) {

        Integer owner=privacyTypeService.getById(privacyType.getId()).getOwnerId();

        if(owner!=null&&!owner.equals(UserUtils.getUserId())){
            return Result.fail(String.format("权限不足，操作失败! user id is %s.",UserUtils.getUserId()));
        }

        boolean status=privacyTypeService.updateById(privacyType);
        if(status){
            return Result.ok("操作成功! param"+privacyType);
        }else{
            return Result.fail("操作失败! param="+privacyType);
        }
    }

    /**
     * 查询隐私类型 查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询隐私类型",notes = "使用{id}占位符，参数需要注解 @PathVariable")
    @GetMapping("/{id}/json")
    public Result<PrivacyType> getPrivacyType(@PathVariable Integer id) {
        PrivacyType privacyType=privacyTypeService.getById(id);

        if(privacyType!=null){
            return Result.ok(privacyType);
        }else{
            return Result.fail("数据库中找不到这条记录哦! Σ(oﾟдﾟoﾉ)");
        }
    }

    /**
     * 查询隐私类型 条件查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "条件查询隐私类型",notes = "使用POST，唯一参数需要注解 @RequestBody ")
    @PostMapping("/list/json")
    public Result<List<PrivacyType>> listPrivacyType(@RequestBody ConditionVO conditionVO) {
        return Result.ok(privacyTypeService.list());
    }
}
