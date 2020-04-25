package com.smile.blog.dao;


import com.smile.blog.domain.Comment;
import com.smile.blog.domain.Reply;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-13 09:11
 */
public interface ReplyMapper extends Mapper<Reply> {

    @Select("SELECT * FROM reply r WHERE r.`level` = #{level} ORDER BY r.time ASC")
    ArrayList<Reply> getReplyByLevel(int level);

    @Select("SELECT r.level FROM reply r WHERE r.parent_id = 1 ORDER   BY r.`level` desc LIMIT 1")
    int num(int cid);

    @Select("SELECT *  FROM blog.reply r WHERE r.uid = #{uid}")
    List<Reply> nowUserReply(String uid);
}
