package com.smile.blog.dao;


import com.smile.blog.domain.BlogTag;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogTagMapper extends Mapper<BlogTag> {
    @Select("select * from blog.blog_tag b where b.bid =#{bid} ")
    List<BlogTag> findByBid(String bid);

    @Select("select b.bid from blog.blog_tag b where b.tid =#{tid} ")
    List<String> findByBTid(Integer tid);
}
