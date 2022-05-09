package com.ve.locker.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.PrivacyTag;
import com.ve.locker.service.IPrivacyTagService;
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
@RequestMapping("/admin/privacy/tag")
public class PrivacyTagController {

    @Autowired
    private IPrivacyTagService privacyTagService;


    /**
     * 添加隐私标签 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加隐私标签",notes = "对传输的数据不做修改存储")
    @PostMapping("/add/json")
    public Result<?> addPrivacyTag(@RequestBody @Valid PrivacyTag privacyTag) {
        privacyTag.setOwnerId(UserUtils.getUserId());
        boolean status=privacyTagService.save(privacyTag);
        if(status){
            return Result.ok("操作成功! param"+privacyTag);
        }else{
            return Result.fail("操作失败! param"+privacyTag);
        }
    }

    /**
     * 删除隐私标签 删
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除隐私标签")
    @DeleteMapping("/delete/json")
    public Result<?> deletePrivacyTag(@Valid Integer id) {

        PrivacyTag privacyTag=privacyTagService.getById(id);
        if(!privacyTag.getOwnerId().equals(UserUtils.getUserId())){
            return Result.fail(String.format("权限不足，操作失败! user id is %s.",UserUtils.getUserId()));
        }

        boolean status=privacyTagService.removeById(id);
        if(status){
            return Result.ok("删除成功！ param:"+id);
        }else{
            return Result.fail("操作失败! param:"+id);
        }
    }


    /**
     * 更新隐私标签 改
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "更新隐私标签")
    @PostMapping("/update/json")
    public Result<?> updatePrivacyTag(@RequestBody @Valid PrivacyTag privacyTag) {

        Integer owner=privacyTagService.getById(privacyTag.getId()).getOwnerId();

        if(owner!=null&&!owner.equals(UserUtils.getUserId())){
            return Result.fail(String.format("权限不足，操作失败! user id is %s.",UserUtils.getUserId()));
        }

        boolean status=privacyTagService.updateById(privacyTag);
        if(status){
            return Result.ok("操作成功! param"+privacyTag);
        }else{
            return Result.fail("操作失败! param="+privacyTag);
        }
    }

    /**
     * 查询隐私标签 查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询隐私标签",notes = "使用{id}占位符，参数需要注解 @PathVariable")
    @GetMapping("/{id}/json")
    public Result<PrivacyTag> getPrivacyTag(@PathVariable Integer id) {
        PrivacyTag privacyTag=privacyTagService.getById(id);

        if(privacyTag!=null){
            return Result.ok(privacyTag);
        }else{
            return Result.fail("数据库中找不到这条记录哦! Σ(oﾟдﾟoﾉ)");
        }
    }

    /**
     * 查询隐私标签 条件查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "条件查询隐私标签",notes = "使用POST，唯一参数需要注解 @RequestBody ")
    @PostMapping("/list/json")
    public Result<List<PrivacyTag>> listPrivacyTag(@RequestBody ConditionVO conditionVO) {
        return Result.ok(privacyTagService.list());
    }
}
