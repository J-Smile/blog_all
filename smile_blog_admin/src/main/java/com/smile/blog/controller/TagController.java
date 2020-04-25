package com.smile.blog.controller;

import com.smile.blog.domain.Tag;
import com.smile.blog.service.TagService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * @author: 淳淳
 * @create: 2020-03-25 11:23
 * @description: 标签
 **/
@RestController
@RequestMapping("/tag")
@Api(description = "标签Controller")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @ApiOperation("获取所有的标签")
    public BaseResult getAll() {
        return BaseResult.success(tagService.getAll());
    }

    @GetMapping("status")
    @ApiOperation("获取正常的标签")
    public BaseResult getTagStatus() {
        return BaseResult.success(tagService.getSortStatus());
    }

    @GetMapping("/{tid}")
    @ApiOperation("修改获取标签")
    public BaseResult getTag(@PathVariable Integer tid) {
        return BaseResult.success(tagService.getTag(tid));
    }


    @PostMapping
    @ApiOperation("添加修改标签")
    public BaseResult addTag(@RequestBody Tag tag) {
        tagService.saveTag(tag);
        return BaseResult.success();
    }


    @DeleteMapping("/{tid}")
    @ApiOperation("删除标签")
    public BaseResult delTag(@PathVariable("tid") Integer tid) {
        tagService.delTag(tid);
        return BaseResult.success();
    }

}
