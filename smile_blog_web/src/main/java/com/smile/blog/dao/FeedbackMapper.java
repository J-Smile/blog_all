package com.smile.blog.dao;

import com.smile.blog.domain.Feedback;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-23 17:12
 */
public interface FeedbackMapper  extends Mapper<Feedback> {

    @Select("select * from blog.feedback f where f.uid= #{uid} order by f.time desc")
    ArrayList<Feedback> getNowUserFeedback(String uid);
}
