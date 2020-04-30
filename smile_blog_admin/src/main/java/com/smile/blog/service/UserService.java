package com.smile.blog.service;

import com.smile.blog.config.Role;
import com.smile.blog.dao.UserDao;
import com.smile.blog.domain.User;
import com.smile.blog.exception.UserException;
import com.smile.blog.utils.JwtUtil;
import com.smile.blog.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author: Smile
 * @description:
 * @create: 2020-03-11 18:18
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    private final RedisUtils redisUtils;
    private final JwtUtil jwtUtil;

    public UserService(JwtUtil jwtUtil, RedisUtils redisUtils) {
        this.jwtUtil = jwtUtil;
        this.redisUtils = redisUtils;
    }


    public String login(User user) {
        boolean flag = StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getPassword());
        if (flag) {
            return null;
        }
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo(user);
        User thisUser = userDao.selectOneByExample(example);
        if (thisUser == null) {
            return null;
        }
        String token = jwtUtil.createToken(Role.ADMIN, thisUser.getUid());
        redisUtils.saveUser(thisUser.getUid(), thisUser);
        return token;
    }


    public User getNowUser(HttpServletRequest request) {
        return redisUtils.getUser(request.getHeader("authorization"));
    }


    @Transactional(rollbackFor = Exception.class)
    public void editUser(User user) {
        try {
            userDao.updateByPrimaryKey(user);
            redisUtils.saveUser(user.getUid(), user);
        } catch (Exception e) {
            throw new UserException("修改用户错误");
        }

    }

    public Integer getUserNum() {
        return userDao.getUserNum();
    }

    public User findByUserId(String uid) {
        return userDao.selectByPrimaryKey(uid);
    }
}
