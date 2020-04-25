package com.smile.blog.controller;

import com.smile.blog.domain.Author;
import com.smile.blog.service.AuthorService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:22
 */
@RequestMapping("author")
@RestController
@Api("网站信息")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ApiOperation("获取网站信息")
    public BaseResult author() {
        return BaseResult.success(authorService.author());
    }

}
