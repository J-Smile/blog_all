package com.smile.blog.controller;


import com.smile.blog.service.NumberService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-08 21:47
 */
@RestController
@RequestMapping("num")
public class NumberController {
    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping
    @ApiOperation("获取联系方式")
    public BaseResult number() {
        return BaseResult.success(numberService.number());
    }

}
