package com.smile.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.smile.blog.config.MaYunClientConfig;
import com.smile.blog.config.Role;
import com.smile.blog.config.Url;
import com.smile.blog.domain.MaYunUser;
import com.smile.blog.domain.User;
import com.smile.blog.utils.JWTUtil;
import com.smile.blog.utils.RedisUtils;
import com.smile.blog.utils.UUid;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-22 18:25
 */
@Service
public class AuthService {
    private final RedisUtils redisUtils;
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public AuthService(RedisUtils redisUtils, JWTUtil jwtUtil, UserService userService) {
        this.redisUtils = redisUtils;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public void login(AuthCallback callback, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse authResponse = authRequest.login(callback);
        String result = JSONObject.toJSONString(authResponse.getData());
        MaYunUser maYunUser = JSONObject.parseObject(result, MaYunUser.class);
        User user = userService.findUserByAvatar(maYunUser.getAvatar());
        if (user == null) {
            user = new User();
            user.setUid(UUid.getId());
            user.setAvatar(maYunUser.getAvatar());
            user.setNickname(maYunUser.getNickname());
            user.setIfadmin(false);
            user.setUsername(maYunUser.getUsername());
            userService.saveUser(user);
        }
        redisUtils.saveUser(user.getUid(), user);
        String token = jwtUtil.createToken(Role.USER, user.getUid());
        response.sendRedirect(Url.WEBSITE_URL + "/" + token);
    }
    private AuthRequest getAuthRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId(MaYunClientConfig.GITEE_CLIENT_ID)
                .clientSecret(MaYunClientConfig.GITEE_CLIENT_SECRET)
                .redirectUri(Url.WEB_URL)
                .build());
    }

    public String renderAuth() {
        AuthRequest authRequest = getAuthRequest();
        return authRequest.authorize(AuthStateUtils.createState());
    }
}
