package com.smile.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile.blog.dao.BlogMapper;
import com.smile.blog.dao.BlogTagMapper;
import com.smile.blog.domain.Blog;
import com.smile.blog.domain.BlogTag;
import com.smile.blog.domain.Tag;
import com.smile.blog.domain.User;
import com.smile.blog.utils.RedisUtils;
import com.smile.blog.utils.UuidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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
    private BlogTagMapper blogTagMapper;
    private final RedisUtils redisUtils;
    private final SortService sortService;
    private final TagService tagService;

    public BlogService(RedisUtils redisUtils, SortService sortService, TagService tagService) {
        this.redisUtils = redisUtils;
        this.sortService = sortService;
        this.tagService = tagService;
    }


    @Transactional(rollbackFor =Exception.class )
    public void addBlog(Blog blog, HttpServletRequest request) {
        blog.setBid(UuidUtils.getId());
        blog.setClickCount(0);
        blog.setStatus(true);
        blog.setCreateTime(new Date());
        User user = redisUtils.getUser(request.getHeader("authorization"));
        blog.setDiscussCount(0);
        blog.setUid(user.getUid());
        blog.setAuthor(user.getUsername());
        blog.setIsPublish(true);
        blogMapper.insert(blog);
        for (int tid : blog.getTids()) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBid(blog.getBid());
            blogTag.setTid(tid);
            blogTagMapper.insert(blogTag);
        }
    }

    public PageInfo<Blog> findBlog(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Blog>(blogMapper.selectAll());
    }

    public void del(String bid) {
        blogMapper.deleteByPrimaryKey(bid);
    }

    @Transactional(rollbackFor = Exception.class)
    public Blog blog(String id) {
        Blog blog = redisUtils.getBlog(id);
        if (blog == null) {
            blog = blogMapper.selectByPrimaryKey(id);
            List<BlogTag> byBid = blogTagMapper.findByBid(id);
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

    public void editBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogMapper.updateByPrimaryKey(blog);

        List<BlogTag> byBid = blogTagMapper.findByBid(blog.getBid());
        byBid.forEach(e -> blogTagMapper.delete(e));
        BlogTag blogTag = new BlogTag();
        blogTag.setBid(blog.getBid());
        for (int tid : blog.getTids()) {
            blogTag.setTid(tid);
            blogTagMapper.insert(blogTag);
        }
        redisUtils.saveBlog(blog.getBid(), blog);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean recommend(String bid) {
        Blog blog = blogMapper.selectByPrimaryKey(bid);
        blog.setLevel(!blog.getLevel());
        blogMapper.updateByPrimaryKey(blog);
        return blog.getLevel();
    }

    public Integer getBlogNum() {
        return blogMapper.getBlogNum();
    }

    public String getBlogTitle(String bid) {
        return blogMapper.selectByPrimaryKey(bid).getTitle();
    }

}
