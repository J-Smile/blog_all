package com.smile.blog.service;

import com.smile.blog.dao.TagMapper;
import com.smile.blog.domain.Tag;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Smile
 */
@Service
public class TagService {
    @Resource
    private TagMapper tagMapper;


    public List<Tag> getAll() {
        return tagMapper.selectAll();
    }

    public void saveTag(Tag tag) {
        if (tag.getTid() != null) {
            tagMapper.updateByPrimaryKey(tag);
        } else {
            tag.setStatus(true);
            tagMapper.insert(tag);
        }
    }


    public void delTag(int tid) {
        tagMapper.deleteByPrimaryKey(tid);
    }

    public Tag getTag(int tid) {
        return tagMapper.selectByPrimaryKey(tid);
    }

    public List<Tag> getSortStatus() {
        return tagMapper.tagStatus();
    }


}
