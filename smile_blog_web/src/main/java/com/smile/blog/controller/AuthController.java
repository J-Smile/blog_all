package com.smile.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.smile.blog.config.Role;
import com.smile.blog.domain.MaYunUser;
import com.smile.blog.domain.User;
import com.smile.blog.service.AuthService;
import com.smile.blog.service.UserService;
import com.smile.blog.utils.JWTUtil;
import com.smile.blog.utils.RedisUtils;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Smile
 * @description: 认证
 * @create: 2020-04-02 15:51
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/render")
    public String renderAuth() {
        return authService.renderAuth();
    }

    @RequestMapping("/callback")
    public void login(AuthCallback callback, HttpServletResponse response) throws IOException {
        authService.login(callback, response);
    }
}

