package com.ve.locker.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.PrivacyFolder;
import com.ve.locker.service.IPrivacyFolderService;
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
@RequestMapping("/admin/privacy/folder")
public class PrivacyFolderController {

    @Autowired
    private IPrivacyFolderService privacyFolderService;

    /**
     * 添加隐私文件夹 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加隐私文件夹",notes = "对传输的数据不做修改存储")
    @PostMapping("/add/json")
    public Result<?> addPrivacyFolder(@RequestBody @Valid PrivacyFolder privacyFolder) {
        privacyFolder.setOwnerId(UserUtils.getUserId());
        boolean status=privacyFolderService.save(privacyFolder);
        if(status){
            return Result.ok("操作成功! param"+privacyFolder);
        }else{
            return Result.fail("操作失败! param"+privacyFolder);
        }
    }

    /**
     * 删除隐私文件夹 删
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除隐私文件夹")
    @DeleteMapping("/delete/json")
    public Result<?> deletePrivacyFolder(@Valid Integer id) {

        PrivacyFolder privacyFolder=privacyFolderService.getById(id);
        if(!privacyFolder.getOwnerId().equals(UserUtils.getUserId())){
            return Result.fail(String.format("权限不足，操作失败! user id is %s.",UserUtils.getUserId()));
        }

        boolean status=privacyFolderService.removeById(id);
        if(status){
            return Result.ok("删除成功！ param:"+id);
        }else{
            return Result.fail("操作失败! param:"+id);
        }
    }


    /**
     * 更新隐私文件夹 改
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "更新隐私文件夹")
    @PostMapping("/update/json")
    public Result<?> updatePrivacyFolder(@RequestBody @Valid PrivacyFolder privacyFolder) {

        Integer owner=privacyFolderService.getById(privacyFolder.getId()).getOwnerId();

        if(owner!=null&&!owner.equals(UserUtils.getUserId())){
            return Result.fail(String.format("权限不足，操作失败! user id is %s.",UserUtils.getUserId()));
        }
        boolean status=privacyFolderService.updateById(privacyFolder);
        if(status){
            return Result.ok("操作成功! param"+privacyFolder);
        }else{
            return Result.fail("操作失败! param="+privacyFolder);
        }
    }

    /**
     * 查询隐私文件夹 查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询隐私文件夹",notes = "使用{id}占位符，参数需要注解 @PathVariable")
    @GetMapping("/{id}/json")
    public Result<PrivacyFolder> getPrivacyFolder(@PathVariable Integer id) {
        PrivacyFolder privacyFolder=privacyFolderService.getById(id);

        if(privacyFolder!=null){
            return Result.ok(privacyFolder);
        }else{
            return Result.fail("数据库中找不到这条记录哦! Σ(oﾟдﾟoﾉ)");
        }
    }

    /**
     * 查询隐私文件夹 条件查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "条件查询隐私文件夹",notes = "使用POST，唯一参数需要注解 @RequestBody ")
    @PostMapping("/list/json")
    public Result<List<PrivacyFolder>> listPrivacyFolder(@RequestBody ConditionVO conditionVO) {
        return Result.ok(privacyFolderService.list());
    }
}
