package com.ve.locker.controller;


import com.ve.locker.service.IKeyService;
import com.ve.locker.util.UserUtils;
import com.ve.locker.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/key")
public class KeyController {
    @Autowired
    IKeyService keyService;

    @ApiOperation(value = "查询当前登录用户私钥")
    @GetMapping("/user/key/privateKey")
    public Result<?> getUserPrivateKey(){
        return Result.ok(keyService.getPrivateKey(UserUtils.getUserId()));
    }

    @ApiOperation(value = "查询当前登录用户公钥")
    @GetMapping("/user/key/publicKey")
    public Result<?> getUserPublicKey(){
        return Result.ok(keyService.getPublicKey(UserUtils.getUserId()));
    }

    @ApiOperation(value = "根据用户id查询私钥")
    @GetMapping("/admin/privateKey")
    public Result<?> getUserPrivateKeyById(Integer userId){
        return Result.ok(keyService.getPrivateKey(userId));
    }

    @ApiOperation(value = "根据用户id查询公钥")
    @GetMapping("/admin/publicKey")
    public Result<?> getUserPublicKeyById(Integer userId){
        return Result.ok(keyService.getPublicKey(userId));
    }

    @ApiOperation(value = "获取秘钥列表")
    @GetMapping("/admin/list")
    public Result<?>  getUserKeys(){
        return Result.ok(keyService.list());
    }
}
