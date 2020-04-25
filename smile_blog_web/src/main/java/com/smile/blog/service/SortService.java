package com.smile.blog.service;


import com.smile.blog.dao.SortMapper;
import com.smile.blog.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 09:57
 */
@Service
public class SortService {

    @Resource
    private SortMapper sortMapper;

    public Sort getSort(Integer sid) {
        return sortMapper.selectByPrimaryKey(sid);
    }

}
