package com.smile.blog.service;

import com.smile.blog.dao.FeedbackMapper;
import com.smile.blog.domain.Feedback;
import com.smile.blog.domain.User;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-23 17:11
 */
@Service
public class FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;
    private final UserService userService;

    public FeedbackService(UserService userService) {
        this.userService = userService;
    }

    public List<Feedback> getNowUserFeedback(HttpServletRequest request) {
        User user = userService.getNowUser(request);
        return feedbackMapper.getNowUserFeedback(user.getUid());
    }


    public void addFeedback(Feedback feedback, HttpServletRequest request) {
        User user = userService.getNowUser(request);
        feedback.setUid(user.getUid());
        feedback.setTime(LocalDateTime.now());
        feedbackMapper.insert(feedback);
    }

}
