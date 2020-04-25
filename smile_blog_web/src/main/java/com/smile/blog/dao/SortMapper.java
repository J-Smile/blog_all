package com.smile.blog.dao;


import com.smile.blog.domain.Sort;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SortMapper extends Mapper<Sort> {

    @Select("select * from blog.sort s where s.status = 1")
    List<Sort> sortStatus();
}
