package com.smile.blog.controller;


import com.smile.blog.domain.Reply;
import com.smile.blog.service.ReplyService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description: 回复
 * @create: 2020-04-21 11:59
 */
@RestController
@RequestMapping("reply")
@Api(description = "回复 Controller")
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation("获取所有回复")
    public BaseResult getAll(@PathVariable Integer page, @PathVariable Integer size) {
        return BaseResult.success(replyService.getAll(page, size));
    }
}
