package com.smile.blog.dao;

import com.smile.blog.domain.Number;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Smile
 */
public interface NumberMapper extends Mapper<Number> {

    @Select("select * from blog.number n where n.id =1")
    Number number();
}
