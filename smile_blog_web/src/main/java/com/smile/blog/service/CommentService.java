package com.smile.blog.service;

import com.smile.blog.dao.CommentMapper;
import com.smile.blog.domain.Blog;
import com.smile.blog.domain.Comment;
import com.smile.blog.domain.Reply;
import com.smile.blog.domain.User;
import com.smile.blog.utils.RedisUtils;
import com.smile.blog.vo.CommentReply;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 20:13
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;
    private final RedisUtils redisUtils;
    private final ReplyService replyService;
    private final UserService userService;
    private final BlogService blogService;

    public CommentService(ReplyService replyService, RedisUtils redisUtils, UserService userService, BlogService blogService) {
        this.replyService = replyService;
        this.redisUtils = redisUtils;
        this.userService = userService;
        this.blogService = blogService;
    }

    public String commentById(int cid) {
        return commentMapper.selectByPrimaryKey(cid).getBid();
    }

    public void saveContent(Comment comment, String token) {
        save(1, comment, token);
        String bid = comment.getBid();
        blogService.addDiscussCount(bid);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(int ismag, @NotNull Comment comment, String token) {
        comment.setIsmsg(ismag);
        User user = redisUtils.getUser(token);
        comment.setUid(user.getUid());
        comment.setTime(LocalDateTime.now());
        commentMapper.insert(comment);
    }

    public void saveMessage(Comment comment, String token) {
        save(0, comment, token);
    }

    public ArrayList<Comment> commentById(String bid) {
        ArrayList<Comment> comments = commentMapper.commentById(bid);
        comments.forEach(e -> {
            e.setReplies(replyService.getReplyByCid(e.getId()));
            e.setUser(userService.findUserById(e.getUid()));
        });

        return comments;
    }

    public List<Comment> all() {
        List<Comment> messageAll = commentMapper.getMessageAll();
        return messageAll;
    }

    public ArrayList<Blog> newComment() {
        ArrayList<String> strings = commentMapper.newComment();
        ArrayList<Blog> blogs = new ArrayList<>();
        strings.stream().
                distinct().
                limit(2).
                forEach(e -> blogs.add(blogService.getById(e)));
        return blogs;
    }

    public List<CommentReply> getNowUserComment(HttpServletRequest request) {
        User user = userService.getNowUser(request);
        ArrayList<Comment> comments = commentMapper.nowUserComment(user.getUid());
        ArrayList<CommentReply> commentReplies = new ArrayList<>();
        comments.forEach(e -> commentReplies.add(new CommentReply(e.getText(), e.getIsmsg(), e.getTime(), e.getBid())));
        return commentReplies;
    }
}
