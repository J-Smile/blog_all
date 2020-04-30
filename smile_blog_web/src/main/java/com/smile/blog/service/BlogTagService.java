package com.smile.blog.service;


import com.smile.blog.dao.BlogTagMapper;
import com.smile.blog.domain.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-06 16:35
 */
@Service
public class BlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    private final BlogService blogService;

    public BlogTagService(BlogService blogService) {
        this.blogService = blogService;
    }

    public List<Blog> getBlogByTid(Integer tid) {
        List<String> byBlogTagId = blogTagMapper.findByBlogTagId(tid);
        return blogService.getBlog(byBlogTagId);
    }
}
