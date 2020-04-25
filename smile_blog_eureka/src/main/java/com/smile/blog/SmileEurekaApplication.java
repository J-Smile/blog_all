package com.smile.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-22 15:07
 */
@SpringBootApplication
@EnableEurekaServer
public class SmileEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmileEurekaApplication.class, args);
    }
}
