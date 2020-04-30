package com.smile.blog.service;


import com.smile.blog.dao.UserDao;
import com.smile.blog.domain.User;
import com.smile.blog.exception.UserException;
import com.smile.blog.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    public void saveUser(User user) {
        userDao.insert(user);
    }

    public User findUserByAvatar(String avatar) {
        return userDao.findUserByAvatar(avatar);
    }

    public User getNowUser(HttpServletRequest request) {
        return redisUtils.getUser(request.getHeader("authorization"));
    }

    public User findUserById(String uid) {
        return userDao.selectByPrimaryKey(uid);
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

}
