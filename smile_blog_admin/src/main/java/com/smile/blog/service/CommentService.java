package com.smile.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile.blog.dao.CommentMapper;
import com.smile.blog.domain.Comment;
import com.smile.blog.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 20:13
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;
    private final BlogService blogService;
    private final UserService userService;
    private final ReplyService replyService;

    public CommentService(UserService userService, BlogService blogService, ReplyService replyService) {
        this.userService = userService;
        this.blogService = blogService;
        this.replyService = replyService;
    }

    public Integer getCommentNum() {
        return commentMapper.getCommentNum();
    }

    public PageInfo<Comment> getAll(Integer flag, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Comment> allComment = null;
        if (flag == 1) {
            allComment = commentMapper.findAllComment();
        } else {
            allComment = commentMapper.findAllLeaveMessage();
        }
        allComment.forEach(e -> {
            e.setUser(userService.findByUserId(e.getUid()));
            if (e.getBid() != null) {
                e.setBlogName(blogService.getBlogTitle(e.getBid()));
            }
        });

        return new PageInfo<>(allComment);
    }

    @Transactional
    public void deleteComment(int commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
        List<Reply> byCommentId = replyService.findByCommentId(commentId);
        byCommentId.forEach(e -> replyService.deleteReply(e));
    }
}

