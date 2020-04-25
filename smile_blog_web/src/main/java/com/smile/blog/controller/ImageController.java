package com.smile.blog.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.smile.blog.domain.Image;
import com.smile.blog.service.ImageService;
import com.smile.blog.utils.MultipartToFileUtils;
import com.smile.blog.utils.QiNiuCloudUtil;
import com.smile.blog.vo.BaseResult;
import com.smile.blog.vo.CodeMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: Smile
 * @description: 图片上传
 * @create: 2020-03-23 19:27
 */

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    /**
     * 获取背景图片
     */
    @GetMapping("bg")
    public BaseResult background() {
        return BaseResult.success(imageService.background());
    }

    /**
     * 获取所有轮播图
     * @return
     */
    @GetMapping
    public BaseResult all() {
        List<Image> all = imageService.all();
        return BaseResult.success(all);
    }

}