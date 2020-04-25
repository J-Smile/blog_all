package com.smile.blog.dao;

import com.smile.blog.domain.Blog;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {

    @Select("SELECT count(*) FROM blog.blog")
    Integer getBlogNum();
}