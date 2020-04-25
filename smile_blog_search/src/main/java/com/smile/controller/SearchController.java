package com.smile.controller;

import com.smile.blog.vo.BaseResult;
import com.smile.domain.Blog;
import com.smile.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-24 11:10
 */
@RestController
@RequestMapping("search")
public class SearchController {

    private final BlogRepository blogRepository;

    public SearchController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("{title}")
    public BaseResult findByTitle(@PathVariable String title) {
        return BaseResult.success(blogRepository.findByTitleLike('*' + title + '*'));
    }

    @PostMapping("add")
    public BaseResult addBlog(@RequestBody com.smile.blog.domain.Blog blog) {

        Blog addBlog = new Blog(blog.getBid(), blog.getTitle(), blog.getImage(),blog.getClickCount(),blog.getDiscussCount(),blog.getCreateTime());
        blogRepository.save(addBlog);
        return BaseResult.success();
    }
}
