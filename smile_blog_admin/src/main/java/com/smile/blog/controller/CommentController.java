package com.smile.blog.controller;


import com.smile.blog.service.CommentService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 20:12
 */
@RestController
@RequestMapping("/comment")
@Api(description = "评论 Controller")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{flag}/{page}/{size}")
    @ApiOperation("获取所有评论")
    public BaseResult getAll(@PathVariable Integer flag, @PathVariable Integer page, @PathVariable Integer size) {
        return BaseResult.success(commentService.getAll(flag, page, size));
    }

    @DeleteMapping("{commentId}")
    public BaseResult deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return BaseResult.success();
    }

}
