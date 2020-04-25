package com.smile.blog.service;

import com.smile.blog.dao.BlogMapper;
import com.smile.blog.dao.SortMapper;
import com.smile.blog.domain.Blog;
import com.smile.blog.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 09:57
 */
@Service
public class SortService {

    @Resource
    private SortMapper sortMapper;
    @Resource
    private BlogMapper blogMapper;


    public List<Sort> getAll() {
        return sortMapper.selectAll();
    }

    public void saveSort(Sort sort) {
        if (sort.getSid() != null) {
            sortMapper.updateByPrimaryKey(sort);
        } else {
            sort.setStatus(true);
            sortMapper.insert(sort);
        }
    }


    @Transactional
    public void delSort(String sid) {
        sortMapper.deleteByPrimaryKey(sid);
        Example example = new Example(Blog.class);
        example.createCriteria().andEqualTo("sortId", sid);
        blogMapper.deleteByExample(example);
    }

    public Sort getSort(Integer sid) {
        return sortMapper.selectByPrimaryKey(sid);
    }

    public List<Sort> getSortStatus() {
        return sortMapper.sortStatus();
    }
}
