package com.smile.blog.feign;

import com.smile.blog.domain.Blog;
import com.smile.blog.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "blog-search-server")
public interface SearchFeign {

    @GetMapping("search/{title}")
    BaseResult findByTitle(@PathVariable String title);

    @PostMapping("search/add")
    BaseResult addBlog(@RequestBody Blog blog);
}
