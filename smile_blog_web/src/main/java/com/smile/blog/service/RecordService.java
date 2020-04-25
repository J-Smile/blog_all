package com.smile.blog.service;

import com.smile.blog.dao.RecordMapper;
import com.smile.blog.domain.Record;
import com.smile.blog.utils.RedisUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-11 13:44
 */
@Service
public class RecordService {
    private final RedisUtils redisUtils;
    private final RecordMapper recordMapper;

    public RecordService(RedisUtils redisUtils, RecordMapper recordMapper) {
        this.redisUtils = redisUtils;
        this.recordMapper = recordMapper;
    }

    public void add() {
        redisUtils.addRecord();
    }

}
