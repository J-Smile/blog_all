package com.smile.blog.feign;

import com.smile.blog.domain.Blog;
import com.smile.blog.vo.BaseResult;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Smile
 */
@FeignClient(value = "blog-search-server")
public interface SearchFeign {

    /**
     * 远程调用 blog-search-server
     * 模糊查询
     * @param title  标题关键字
     * @return 博客
     */
    @GetMapping("search/{title}")
    BaseResult findByTitle(@PathVariable String title);

    /** 远程调用 blog-search-server
     *  数据库和es 两边同步
     *  TODO: 2020-04-30 同步换位置
     * @param blog
     * @return
     */

    @PostMapping("search/add")
    BaseResult addBlog(@RequestBody Blog blog);
}
