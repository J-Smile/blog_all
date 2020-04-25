package com.smile.blog.service;

import com.smile.blog.dao.AuthorMapper;
import com.smile.blog.domain.Author;
import com.smile.blog.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:23
 */
@Service
public class AuthorService {

    @Resource
    private AuthorMapper authorMapper;
    private final RedisUtils redisUtils;

    public AuthorService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    public Author author() {
        Author author = redisUtils.getAuthor();
        if (author != null) {
            return author;
        }
        author = authorMapper.author();
        return author;
    }

    public void editAuthor(Author author) {
        authorMapper.updateByPrimaryKey(author);
        redisUtils.saveAuthor(author);
    }
}
