package com.ve.locker.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.UserPrivacyPass;
import com.ve.locker.service.IUserPrivacyPassService;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.PassingPrivacyInfo;
import com.ve.locker.vo.Result;
import com.ve.locker.vo.UserPrivacyPassInfoVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/user/privacy/pass")
public class UserPrivacyPassController {
    @Autowired
    private IUserPrivacyPassService userPrivacyPassService;

    /**
     * 添加隐私密码信息 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加隐私密码信息",notes = "对传输的数据不做修改存储")
    @PostMapping("/admin/add")
    public Result<?> addUserPrivacyInfo(@RequestBody @Valid UserPrivacyPass userPrivacyPass) {
        boolean status=userPrivacyPassService.save(userPrivacyPass);
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
    public Result<?> deleteUserPrivacyInfo(@Valid Integer userPrivacyPassId) {
        boolean status=userPrivacyPassService.removeById(userPrivacyPassId);
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
    public Result<?> updateUserPrivacyInfo(@RequestBody @Valid UserPrivacyPass userPrivacyPass) {
        boolean status=userPrivacyPassService.updateById(userPrivacyPass);
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
    public Result<UserPrivacyPass> getPrivacyInfoById(Integer id) {
        return Result.ok(userPrivacyPassService.getById(id));
    }

    /**
     * 查询隐私密码信息 查
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询隐私密码信息",notes = "传json，数据放body")
    @GetMapping("/admin/list")
    public Result<List<UserPrivacyPass>> getPrivacyInfo() {
        return Result.ok(userPrivacyPassService.list());
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
    public Result<?> addUserPrivacyInfoPass(@RequestBody @Valid UserPrivacyPassInfoVO userPrivacyInfoVO) {
        userPrivacyPassService.addPrivacyInfo(userPrivacyInfoVO);
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
    public Result<?> deleteUserPrivacyInfoPass(@Valid Integer privacyInfoId) {
        userPrivacyPassService.deletePrivacyInfo(privacyInfoId);
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
    public Result<?> updateUserPrivacyInfoPass(@RequestBody @Valid UserPrivacyPassInfoVO userPrivacyInfoVO) {
        userPrivacyPassService.updatePrivacyInfo(userPrivacyInfoVO);
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
    public Result<?> getUserPrivacyInfoPass(ConditionVO conditionVO) {
        List<UserPrivacyPassInfoVO> privacyInfoList=userPrivacyPassService.listUserPrivacyInfo(conditionVO);
        return Result.ok(privacyInfoList);
    }



    /**
     * 解析一条隐私信息
     *
     * @return {@link Result <>}
     */
    @ApiOperation(value = "解析一条隐私信息",notes = "返回加密/解密后的对象")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求解析成功"),
            @ApiResponse(code = 510, message = "私钥错误或者对象无法通过解析"),
            @ApiResponse(code = 520, message = "私钥错误或者对象无法通过解析")
    })
    @PostMapping("/parsing")
    public Result<?> parsingPrivacyInfo(@RequestBody @Valid PassingPrivacyInfo passingPrivacyInfo) {
        return Result.ok(userPrivacyPassService.parsingPrivacyInfo(passingPrivacyInfo));
    }
}
