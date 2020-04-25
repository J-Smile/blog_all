package com.smile.blog.controller;

import com.smile.blog.domain.User;
import com.smile.blog.service.UserService;
import com.smile.blog.vo.BaseResult;
import com.smile.blog.vo.CodeMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-11 18:19
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户 Controller")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @ApiOperation("获取当前用户")
    public BaseResult getNowUser(HttpServletRequest request) {
        return BaseResult.success(userService.getNowUser(request));
    }

    @PutMapping
    public BaseResult editUser(@RequestBody User user) {
        userService.editUser(user);
        return BaseResult.success();
    }

}
