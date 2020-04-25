package com.smile.blog.controller;


import com.smile.blog.service.SmileService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Smile
 * @description: 后台首页
 * @create: 2020-04-11 15:12
 */
@RestController
@RequestMapping("/smile")
@Api("首页 Controller")
public class SmileController {
    private final SmileService smileService;

    public SmileController(SmileService smileService) {
        this.smileService = smileService;
    }

    @GetMapping
    @ApiOperation("后台首页信息")
    public BaseResult index() {
        return BaseResult.success(smileService.index());
    }
}
