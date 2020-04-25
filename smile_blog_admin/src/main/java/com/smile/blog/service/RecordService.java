package com.smile.blog.service;

import com.smile.blog.dao.RecordMapper;
import com.smile.blog.domain.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author: Smile
 * @description:
 * @create: 2020-04-11 13:44
 */
@Service
public class RecordService {

    @Resource
    private RecordMapper recordMapper;

    public List<Record> getRecord() {
        return recordMapper.selectAll();
    }
}
