package com.smile.blog.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile.blog.dao.ReplyMapper;
import com.smile.blog.domain.Reply;
import com.smile.blog.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-13 09:10
 */
@Service
public class ReplyService {

    @Resource
    private ReplyMapper replyMapper;
    private final UserService userService;

    public ReplyService(UserService userService) {
        this.userService = userService;
    }


    public List<Reply> findByCommentId(int findByCommentId) {
        return replyMapper.findByCommentId(findByCommentId);
    }

    public void deleteReply(Reply e) {
        replyMapper.delete(e);
    }

    public PageInfo<Reply> getAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Reply> replies = replyMapper.selectAll();
        replies.forEach(e -> {
            User user = userService.findByUserId(e.getUid());
            e.setUser(user);
            if (!"0".equals(e.getToUid())){
                User toUser = userService.findByUserId(e.getToUid());
                e.setToUserName(toUser.getNickname());
            }
        });
        return new PageInfo<>(replies);

    }
}
