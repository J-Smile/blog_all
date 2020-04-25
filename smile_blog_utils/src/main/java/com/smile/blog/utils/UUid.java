package com.smile.blog.utils;

import java.util.UUID;

public class UUid {
    public static void main(String[] args) {
        System.out.println(getId());
    }
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}