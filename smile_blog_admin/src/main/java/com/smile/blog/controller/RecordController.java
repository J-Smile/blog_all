package com.smile.blog.controller;


import com.smile.blog.service.RecordService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-11 13:44
 */
@RestController
@RequestMapping("record")
@Api("流量 Controller")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("get")
    @ApiOperation("获取日流量")
    public BaseResult getRecord() {
        return BaseResult.success(recordService.getRecord());
    }
}
