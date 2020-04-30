package com.smile.blog.service;

import com.smile.blog.dao.AuthorMapper;
import com.smile.blog.domain.Author;
import com.smile.blog.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:23
 */
@Service
public class AuthorService {

    private final RedisUtils redisUtils;

    public AuthorService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


}
