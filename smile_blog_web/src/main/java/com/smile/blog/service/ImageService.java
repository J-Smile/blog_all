package com.smile.blog.service;

import com.smile.blog.dao.ImageMapper;
import com.smile.blog.domain.Image;
import com.smile.blog.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-01 16:12
 */
@Service
public class ImageService {

    @Resource
    private ImageMapper imageMapper;
    private final RedisUtils redisUtils;

    public ImageService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    public Image background() {
        Image backGround = redisUtils.getBackGround();
        if (backGround != null) {
            return backGround;
        }
        backGround = imageMapper.background();
        redisUtils.saveBackGround(backGround);
        return backGround;
    }
    public List<Image> all() {
        List<Image> all = (List<Image>) redisUtils.getLunBo();
        if (all != null) {
            return all;
        }
        all = imageMapper.all();
        redisUtils.saveLunBo(all);
        return all;
    }

}
