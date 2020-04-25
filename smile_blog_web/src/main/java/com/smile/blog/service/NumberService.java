package com.smile.blog.service;

import com.smile.blog.dao.NumberMapper;
import com.smile.blog.domain.Number;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-08 21:47
 */
@Service
public class NumberService {

    @Resource
    private NumberMapper numberMapper;

    public Number number() {
        return numberMapper.number();
    }
}
