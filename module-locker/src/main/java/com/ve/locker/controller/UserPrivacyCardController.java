package com.ve.locker.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.UserPrivacyCard;
import com.ve.locker.service.IUserPrivacyCardService;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.Result;
import com.ve.locker.vo.UserPrivacyCardInfoVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/user/privacy/card")
public class UserPrivacyCardController {
    @Autowired
    private IUserPrivacyCardService userPrivacyCardService;

    /**
     * 添加隐私密码信息 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加隐私密码信息",notes = "对传输的数据不做修改存储")
    @PostMapping("/admin/add")
    public Result<?> addUserPrivacyInfo(@RequestBody @Valid UserPrivacyCard userPrivacyCard) {
        boolean status=userPrivacyCardService.save(userPrivacyCard);
        if(status){
            return Result.ok();
        }else{
            return Result.fail("操作失败!");
        }
    }

    /**
     * 删除隐私密码信息 删
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除隐私密码信息")
    @DeleteMapping("/admin/delete")
    public Result<?> deleteUserPrivacyInfo(@Valid Integer userPrivacyCardId) {
        boolean status=userPrivacyCardService.removeById(userPrivacyCardId);
        if(status){
            return Result.ok();
        }else{
            return Result.fail("操作失败!");
        }
    }
    /**
     * 更新隐私密码信息 改
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "更新隐私密码信息")
    @PostMapping("/admin/update")
    public Result<?> updateUserPrivacyInfo(@RequestBody @Valid UserPrivacyCard userPrivacyCard) {
        boolean status=userPrivacyCardService.updateById(userPrivacyCard);
        if(status){
            return Result.ok();
        }else{
            return Result.fail("操作失败!");
        }
    }

    /**
     * 查询隐私密码信息 查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询隐私密码信息",notes = "传json，数据放body")
    @GetMapping("/admin/query")
    public Result<UserPrivacyCard> getPrivacyInfoById(Integer id) {
        return Result.ok(userPrivacyCardService.getById(id));
    }

    /**
     * 查询隐私密码信息 查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询隐私密码信息",notes = "传json，数据放body")
    @GetMapping("/admin/list")
    public Result<List<UserPrivacyCard>> getPrivacyInfo() {
        return Result.ok(userPrivacyCardService.list());
    }


/************************** 公开接口 **********************/

    /**
     * 增 添加隐私信息
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "添加隐私信息",notes = "对传输的数据不做修改存储")
    @PostMapping("/add")
    public Result<?> addUserPrivacyInfoCard(@RequestBody @Valid UserPrivacyCardInfoVO userPrivacyInfoVO) {
        userPrivacyCardService.addPrivacyInfo(userPrivacyInfoVO);
        return Result.ok();
    }

    /**
     * 删 删除隐私信息
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "删除隐私信息")
    @DeleteMapping("/delete")
    public Result<?> deleteUserPrivacyInfoCard(@Valid Integer privacyInfoId) {
        userPrivacyCardService.deletePrivacyInfo(privacyInfoId);
        return Result.ok();
    }

    /**
     * 改 更新隐私信息
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "更新隐私信息")
    @PostMapping("/update")
    public Result<?> updateUserPrivacyInfoCard(@RequestBody @Valid UserPrivacyCardInfoVO userPrivacyInfoVO) {
        userPrivacyCardService.updatePrivacyInfo(userPrivacyInfoVO);
        return Result.ok();
    }
    /**
     * 查 查询当前用户隐私列表
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "查询当前用户隐私列表")
    @GetMapping("/info")
    public Result<?> getUserPrivacyInfoCard(ConditionVO conditionVO) {
        List<UserPrivacyCardInfoVO> privacyInfoList=userPrivacyCardService.listUserPrivacyInfo(conditionVO);
        return Result.ok(privacyInfoList);
    }
}
