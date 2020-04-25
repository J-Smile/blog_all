package com.smile.blog.service;

import com.smile.blog.domain.Blog;
import com.smile.blog.feign.SearchFeign;
import com.smile.blog.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-24 11:26
 */
@Service
public class SearchService {

    private final SearchFeign searchFeign;
    private final BlogService blogService;

    public SearchService(SearchFeign searchFeign, BlogService blogService) {
        this.searchFeign = searchFeign;
        this.blogService = blogService;
    }

    public BaseResult findByTitle(String title) {
        return searchFeign.findByTitle(title);
    }

    public void addBlog() {
        List<Blog> all = blogService.findAll();
        all.forEach(e->searchFeign.addBlog(e));
    }
}
