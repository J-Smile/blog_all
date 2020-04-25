package com.smile.blog.controller;

import com.smile.blog.domain.Feedback;
import com.smile.blog.service.FeedbackService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-23 17:11
 */
@RestController
@RequestMapping("feedback")
@Api(description = "反馈 Controller")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("nowUser")
    @ApiOperation("获取当前用户的反馈")
    public BaseResult getNowUserFeedback(HttpServletRequest request) {
        return BaseResult.success(feedbackService.getNowUserFeedback(request));
    }

    @PostMapping
    @ApiOperation("添加反馈")
    public BaseResult addFeedback (@RequestBody Feedback feedback,HttpServletRequest request){
        feedbackService.addFeedback(feedback,request);
        return BaseResult.success();
    }
}
