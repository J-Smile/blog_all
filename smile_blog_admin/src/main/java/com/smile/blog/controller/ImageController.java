package com.smile.blog.controller;


import com.smile.blog.domain.Image;
import com.smile.blog.service.ImageService;
import com.smile.blog.utils.MultipartToFileUtils;
import com.smile.blog.utils.QiNiuCloudUtil;
import com.smile.blog.vo.BaseResult;
import com.smile.blog.vo.CodeMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "图片 Controller")
public class ImageController {
    private final QiNiuCloudUtil qiNiuCloudUtil;
    private final ImageService imageService;

    public ImageController(ImageService imageService, QiNiuCloudUtil qiNiuCloudUtil) {
        this.imageService = imageService;
        this.qiNiuCloudUtil = qiNiuCloudUtil;
    }


    @PostMapping
    @ApiOperation("上传图片")
    public BaseResult upload(MultipartFile file) {
        try {
            File newFile = MultipartToFileUtils.multipartToFile(file);
            String url = qiNiuCloudUtil.upload(newFile, file.getOriginalFilename());
            newFile.delete();
            return BaseResult.success(url);
        } catch (IOException e) {
            return BaseResult.error(CodeMessage.IO_ERROR);
        }
    }

    @GetMapping
    @ApiOperation("获取所有轮播图")
    public BaseResult all() {
        List<Image> all = imageService.all();
        return BaseResult.success(all);
    }

    @GetMapping("bg")
    @ApiOperation("获取背景图片")
    public BaseResult background() {
        return BaseResult.success(imageService.background());
    }

    @PutMapping
    @ApiOperation("修改轮播图")
    public BaseResult editLunBo(@RequestBody Image image) {
        imageService.editLunBo(image);
        return BaseResult.success();
    }

    @PutMapping("bg")
    @ApiOperation("修改背景图片")
    public BaseResult editBackground(@RequestBody Image image) {
        imageService.editBackground(image);
        return BaseResult.success();
    }



}