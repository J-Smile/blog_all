package com.smile.blog.dao;


import com.smile.blog.domain.Tag;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Smile
 */
public interface TagMapper extends Mapper<Tag> {
    @Select("select * from blog.tag t where t.status = 1")
    List<Tag> tagStatus();
}