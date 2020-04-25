package com.smile.blog.controller;

import com.smile.blog.domain.Sort;
import com.smile.blog.service.SortService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description: 分类
 * @create: 2020-03-25 09:56
 */
@RestController
@RequestMapping("/sort")
@Api(description = "分类Controller")
public class SortController {
    private final SortService sortService;

    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @GetMapping
    @ApiOperation("获取所有的分类")
    public BaseResult getAll() {
        return BaseResult.success(sortService.getAll());
    }

    @GetMapping("status")
    @ApiOperation("获取正常的分类")
    public BaseResult getSortStatus() {
        return BaseResult.success(sortService.getSortStatus());
    }

    @GetMapping("/{sid}")
    @ApiOperation("修改时根据id获取分类")
    public BaseResult getSort(@PathVariable Integer sid) {
        return BaseResult.success(sortService.getSort(sid));
    }

    /**
     * 添加和修改
     *
     * @param
     * @return
     */
    @PostMapping
    @ApiOperation("添加修改分类")
    public BaseResult addSort(@RequestBody Sort sort) {
        sortService.saveSort(sort);
        return BaseResult.success();
    }

    @DeleteMapping("/{sid}")
    @ApiOperation("添删除分类")
    public BaseResult delSort(@PathVariable("sid") String sid) {
        sortService.delSort(sid);
        return BaseResult.success();
    }

}
