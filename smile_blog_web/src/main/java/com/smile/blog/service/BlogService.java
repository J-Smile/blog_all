package com.smile.blog.service;


import com.smile.blog.dao.BlogMapper;
import com.smile.blog.dao.BlogTagMapper;
import com.smile.blog.domain.Blog;
import com.smile.blog.domain.BlogTag;
import com.smile.blog.domain.Tag;
import com.smile.blog.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: 淳淳
 * @create: 2020-03-25 18:19
 * @description:
 **/
@Service
public class BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private BlogTagMapper BTMapper;
    private final RedisUtils redisUtils;
    private final SortService sortService;
    private final TagService tagService;

    public BlogService(RedisUtils redisUtils, SortService sortService, TagService tagService) {
        this.redisUtils = redisUtils;
        this.sortService = sortService;
        this.tagService = tagService;
    }

    public Blog getById(String bid) {
        return blogMapper.selectByPrimaryKey(bid);
    }

    public List<Blog> findAll(){
        return blogMapper.selectAll();
    }
    @Transactional
    public Blog blog(String id) {
        Blog blog = redisUtils.getBlog(id);
        if (blog == null) {
            blog = blogMapper.selectByPrimaryKey(id);
            List<BlogTag> byBid = BTMapper.findByBid(id);
            ArrayList<Tag> tags = new ArrayList<>();
            byBid.forEach(e -> tags.add(tagService.getTag(e.getTid())));
            blog.setTags(tags);
            int[] ints = new int[tags.size()];
            for (int i = 0; i < tags.size(); i++) {
                ints[i] = tags.get(i).getTid();
            }
            blog.setTids(ints);
            blog.setSortName(sortService.getSort(blog.getSortId()).getContent());
            redisUtils.saveBlog(id, blog);
        }

        blog.setClickCount(blog.getClickCount() + 1);
        blogMapper.updateByPrimaryKey(blog);
        return blog;
    }

    public List<Blog> index() {
        List<Blog> index = blogMapper.index();
        index.forEach(e -> e.setSortName(sortService.getSort(e.getSortId()).getContent()));
        return index;
    }

    public List<Blog> recommend() {
        return blogMapper.recommend();
    }

    /**
     * 根据bid 分装blog
     */
    public List<Blog> getBlog(List<String> bids) {
        ArrayList<Blog> blogs = new ArrayList<>();
        bids.forEach(e -> {
            Blog blog = blogMapper.selectByPrimaryKey(e);
            blogs.add(blog);
            blogs.forEach(e1 -> e1.setSortName(sortService.getSort(e1.getSortId()).getContent()));
        });
        return blogs;
    }


    public void addDiscussCount(String bid) {
        Blog blog = getById(bid);
        blog.setDiscussCount(blog.getDiscussCount() + 1);
    }
}
