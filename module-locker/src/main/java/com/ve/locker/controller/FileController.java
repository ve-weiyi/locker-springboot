package com.ve.locker.controller;

import com.ve.locker.enums.FilePathEnum;
import com.ve.locker.strategy.context.UploadStrategyContext;
import com.ve.locker.util.UserUtils;
import com.ve.locker.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description create for locker-plus .
 * @Author weiyi
 * @Date 2022/4/20
 */
@RestController
@RequestMapping("/user/file")
public class FileController {

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 上传说说图片
     *
     * @param file 文件
     * @return {@link Result <String>} 说说图片地址
     */
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "MultipartFile")
    @PostMapping("/upload")
    public Result<String> saveDataBaseFile(MultipartFile file) {
        return Result.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.DATABASE.getPath()+ UserUtils.getUserId()+"/"));
    }


}
