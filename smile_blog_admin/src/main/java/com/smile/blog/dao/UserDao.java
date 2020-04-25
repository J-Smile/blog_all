package com.smile.blog.dao;


import com.smile.blog.domain.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    @Select("select count(*) from blog.user u where u.ifadmin = 0")
    Integer getUserNum();
}
