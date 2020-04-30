package com.smile.blog.controller;

import com.smile.blog.domain.Author;
import com.smile.blog.feign.AuthorFeign;
import com.smile.blog.service.AuthorService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:22
 */
@RequestMapping("author")
@RestController
@Api(tags = "author")
public class AuthorController {

    private final AuthorFeign authorFeign;

    public AuthorController(AuthorFeign authorFeign) {
        this.authorFeign = authorFeign;
    }


    @GetMapping
    @ApiOperation("获取网站信息")
    public BaseResult author() {
        return authorFeign.author();
    }

}
