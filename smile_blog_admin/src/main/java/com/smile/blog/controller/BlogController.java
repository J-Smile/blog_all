package com.smile.blog.controller;

import com.smile.blog.domain.Blog;
import com.smile.blog.exception.BlogException;
import com.smile.blog.service.BlogService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 20:25
 */
@RestController
@RequestMapping("/blog")
@Api(description = "博客 Controller")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation("分页获取博客")
    public BaseResult findBlog(@PathVariable int page, @PathVariable int size) {
        return BaseResult.success(blogService.findBlog(page, size));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取博客")
    public BaseResult blog(@PathVariable String id) {
        Blog blog = null;
        try {
            blog = blogService.blog(id);
        } catch (Exception e) {
            throw new BlogException("查询参数异常"+id);
        }
        return BaseResult.success(blog);
    }


    @PostMapping
    @ApiOperation("添加博客")
    public BaseResult addBlog(@RequestBody Blog blog, HttpServletRequest request) {
        blogService.addBlog(blog, request);
        return BaseResult.success();
    }

    @PostMapping("/{bid}")
    @ApiOperation("将博客添加为推荐")
    public BaseResult recommend(@PathVariable String bid) {
        Boolean recommend = blogService.recommend(bid);
        return BaseResult.success(recommend);
    }

    @PutMapping
    @ApiOperation("修改博客")
    public BaseResult editBlog(@RequestBody Blog blog) {
        blogService.editBlog(blog);
        return BaseResult.success();
    }

    @DeleteMapping("/{bid}")
    @ApiOperation("删除博客")
    public BaseResult del(@PathVariable String bid) {
        blogService.del(bid);
        return BaseResult.success();
    }
}
