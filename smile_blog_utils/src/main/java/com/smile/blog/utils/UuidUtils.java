package com.smile.blog.utils;

import java.util.UUID;

public class UuidUtils {
    public static void main(String[] args) {
        System.out.println(getId());
    }
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}