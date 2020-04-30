package com.smile.blog.controller;

import com.smile.blog.service.BlogTagService;
import com.smile.blog.service.TagService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Smile
 */
@RestController
@RequestMapping("/tag")
@Api(description = "标签 Controller")
public class TagController {

    private final TagService tagService;
    private final BlogTagService blogTagService;

    public TagController(BlogTagService blogTagService, TagService tagService) {
        this.blogTagService = blogTagService;
        this.tagService = tagService;
    }


    @GetMapping("/tags/{tid}")
    @ApiOperation("根据标签获取 博客")
    public BaseResult getTagByTid(@PathVariable Integer tid) {
        return BaseResult.success(blogTagService.getBlogByTid(tid));
    }

    @GetMapping("status")
    @ApiOperation("获取正常的标签")
    public BaseResult getTagStatus() {
        return BaseResult.success(tagService.getSortStatus());
    }
}
