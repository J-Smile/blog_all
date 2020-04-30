package com.smile.blog.feign;

import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Smile
 */
@FeignClient(value = "blog-search-server")
public interface AuthorFeign {

    /**
     * 调用返回 网站信息
     * @return
     */
    @GetMapping("author")
    BaseResult author();

}
