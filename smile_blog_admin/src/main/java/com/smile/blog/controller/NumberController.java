package com.smile.blog.controller;

import com.smile.blog.domain.Number;
import com.smile.blog.service.NumberService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-08 21:47
 */
@RestController
@RequestMapping("num")
@Api(description = "联系方式 Controller")
public class NumberController {
    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping
    @ApiOperation("获取所有的联系方式")
    public BaseResult number() {
        return BaseResult.success(numberService.number());
    }

    @PutMapping
    @ApiOperation("修改联系方式")
    public BaseResult editNumber(@RequestBody Number number) {
        numberService.editNumber(number);
        return BaseResult.success();
    }

}
