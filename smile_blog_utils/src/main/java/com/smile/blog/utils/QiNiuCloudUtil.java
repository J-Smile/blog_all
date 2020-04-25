package com.smile.blog.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.smile.blog.config.QiNiuYunConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author yintianhao
 * @createTime 07 21:07
 * @description 七牛云工具
 */
@Component
public class QiNiuCloudUtil {

    private final Auth auth = Auth.create(QiNiuYunConfig.ACCESS_KEY, QiNiuYunConfig.SECRET_KEY);

    public String upload(File file, String key) {
        // 创建上传对象，Zone*代表地区
        Configuration configuration = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configuration);

        try {
            // 调用put方法上传
            String token = auth.uploadToken(QiNiuYunConfig.BUCKET_NAME);
            if (StringUtils.isEmpty(token)) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            Response res = uploadManager.put(file, key, token);
            if (res.isOK()) {
                return key;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            e.printStackTrace();
            try {
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                System.out.println("error " + e1.error());
            }
        }
        return null;
    }
}

