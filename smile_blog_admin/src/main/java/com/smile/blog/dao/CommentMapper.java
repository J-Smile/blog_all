package com.smile.blog.dao;


import com.smile.blog.domain.Comment;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

public interface CommentMapper extends Mapper<Comment> {


    @Select("select count(*) from blog.comment c where c.ismsg=1")
    Integer getCommentNum();


    @Select("select * from blog.comment c where c.ismsg=1")
    List<Comment> findAllComment();

    @Select("select * from blog.comment c where c.ismsg=0")
    List<Comment> findAllLeaveMessage();

}
