package com.smile.blog.dao;


import com.smile.blog.domain.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Smile
 */
public interface UserDao extends Mapper<User> {
    @Select("select * from blog.user u where u.ifadmin=0 and u.avatar = #{avatar} limit 1  ")
    User findUserByAvatar(String avatar);

}
