package com.smile.blog.service;


import com.smile.blog.dao.ReplyMapper;
import com.smile.blog.domain.Comment;
import com.smile.blog.domain.Reply;
import com.smile.blog.domain.User;
import com.smile.blog.vo.CommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @create: 2020-04-13 09:10
 */
@Service
public class ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    private  CommentService commentService;
    private final UserService userService;

    public ReplyService(UserService userService ) {
        this.userService = userService;

    }

    /**
     * 评论
     *
     * @param cid 评论id
     * @return
     */
    public ArrayList<Reply> getReplyByCid(int cid) {
        int num = replyMapper.num(cid);
        ArrayList<Reply> replies = replyMapper.getReplyByLevel(1);
        replies.forEach(e -> {
            for (int i = 2; i <= num; i++) {
                e.setReplies(replyMapper.getReplyByLevel(i));
                e.setUser(userService.findUserById(e.getUid()));
            }
        });
        return replies;

    }

    public void replyAdd(Reply reply, HttpServletRequest request) {
        User nowUser = userService.getNowUser(request);
        reply.setUid(nowUser.getUid());
    }

    public Object getNowUserReply(HttpServletRequest request) {
        User user = userService.getNowUser(request);
        List<Reply> replies = replyMapper.nowUserReply(user.getUid());
        ArrayList<CommentReply> commentReplies = new ArrayList<>();
        replies.forEach(x -> commentReplies.add(new CommentReply(x.getText(), 3, x.getTime(), commentByRid(x.getId()))));
        Function<CommentReply, LocalDateTime> extract = CommentReply::getTime;
        Comparator<CommentReply> comparing = Comparator.comparing(extract);
        return commentReplies.stream()
                .limit(6)
                .sorted(comparing.reversed())
                .collect(Collectors.toList());
    }

    public String commentByRid(int rid) {
        Reply reply = replyMapper.selectByPrimaryKey(rid);
        return commentService.commentById(reply.getParentId());
    }
}
