package com.smile.blog.dao;


import com.smile.blog.domain.Reply;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author: Smile
 * @description:
 * @create: 2020-04-13 09:11
 */
public interface ReplyMapper extends Mapper<Reply> {

    @Select("select * from blog.reply r where r.parent_id=#{findByCommentId}")
    List<Reply> findByCommentId(int findByCommentId);
}
