package com.smile.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-22 16:20
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.smile.blog.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
