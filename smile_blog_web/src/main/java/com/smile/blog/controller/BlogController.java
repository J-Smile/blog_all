package com.smile.blog.controller;


import com.smile.blog.domain.Blog;
import com.smile.blog.exception.BlogException;
import com.smile.blog.service.BlogService;
import com.smile.blog.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 20:25
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @GetMapping("/{id}")
    public BaseResult blog(@PathVariable String id) {
        Blog blog = null;
        try {
            blog = blogService.blog(id);
        } catch (Exception e) {
            throw new BlogException("查询参数异常" + id);
        }
        return BaseResult.success(blog);
    }

    /**
     * 获取所有 推荐文章
     *
     * @return
     */
    @GetMapping("/recommend")
    public BaseResult recommendAll() {
        return BaseResult.success(blogService.recommend());
    }

    @GetMapping("/index")
    public BaseResult index() {
        return BaseResult.success(blogService.index());
    }

}
