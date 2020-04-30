package com.smile.blog.dao;


import com.smile.blog.domain.Image;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author Smile
 */
public interface ImageMapper extends Mapper<Image> {

    @Select("select i.id, image from blog.image i where i.id != 4")
    List<Image> all();

    @Select("select i.id, image from blog.image i where i.id = 4")
    Image background();
}
