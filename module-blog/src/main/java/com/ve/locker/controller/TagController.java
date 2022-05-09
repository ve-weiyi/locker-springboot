package com.ve.locker.controller;


import com.ve.locker.annotation.OptLog;
import com.ve.locker.constant.OptTypeConst;
import com.ve.locker.dto.TagBackDTO;
import com.ve.locker.dto.TagDTO;
import com.ve.locker.service.TagService;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.PageResult;
import com.ve.locker.vo.Result;
import com.ve.locker.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 标签控制器
 *
 * @author yezhiqiu
 * @date 2021/07/29
 */
@Api(tags = "标签模块")
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询标签列表
     *
     * @return {@link Result < TagDTO >} 标签列表
     */
    @ApiOperation(value = "查询标签列表")
    @GetMapping("/tags")
    public Result<PageResult<TagDTO>> listTags() {
        return Result.ok(tagService.listTags());
    }

    /**
     * 查询后台标签列表
     *
     * @param condition 条件
     * @return {@link Result< TagBackDTO >} 标签列表
     */
    @ApiOperation(value = "查询后台标签列表")
    @GetMapping("/admin/tags")
    public Result<PageResult<TagBackDTO>> listTagBackDTO(ConditionVO condition) {
        return Result.ok(tagService.listTagBackDTO(condition));
    }

    /**
     * 搜索文章标签
     *
     * @param condition 条件
     * @return {@link Result<String>} 标签列表
     */
    @ApiOperation(value = "搜索文章标签")
    @GetMapping("/admin/tags/search")
    public Result<List<TagDTO>> listTagsBySearch(ConditionVO condition) {
        return Result.ok(tagService.listTagsBySearch(condition));
    }

    /**
     * 添加或修改标签
     *
     * @param tagVO 标签信息
     * @return {@link Result<>}
     */
    @OptLog(optType = OptTypeConst.SAVE_OR_UPDATE)
    @ApiOperation(value = "添加或修改标签")
    @PostMapping("/admin/tags")
    public Result<?> saveOrUpdateTag(@Valid @RequestBody TagVO tagVO) {
        tagService.saveOrUpdateTag(tagVO);
        return Result.ok();
    }

    /**
     * 删除标签
     *
     * @param tagIdList 标签id列表
     * @return {@link Result<>}
     */
    @OptLog(optType = OptTypeConst.REMOVE)
    @ApiOperation(value = "删除标签")
    @DeleteMapping("/admin/tags")
    public Result<?> deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return Result.ok();
    }

}

