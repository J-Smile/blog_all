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

    @PostMapping
    @ApiOperation("添加评论")
    public BaseResult replyAdd(@RequestBody Reply reply, HttpServletRequest request) {
        replyService.replyAdd(reply,request);
        return BaseResult.success();
    }

    @GetMapping("nowUser")
    @ApiOperation("获取当前用户的回复")
    public BaseResult getNowUserComment(HttpServletRequest request) {
        return BaseResult.success(replyService.getNowUserReply(request));
    }
}
