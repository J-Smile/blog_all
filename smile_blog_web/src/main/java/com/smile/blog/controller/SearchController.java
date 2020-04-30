package com.smile.blog.controller;

import com.smile.blog.service.SearchService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-24 11:18
 */
@RestController
@RequestMapping("search")
@Api("es搜索")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{title}")
    @ApiOperation("模糊查询博客")
    public BaseResult findByTitle(@PathVariable String title) {
        return searchService.findByTitle(title);
    }

    @GetMapping
    @ApiOperation("数据库和es同步")
    public void addBlog() {
        searchService.addBlog();
    }
}
