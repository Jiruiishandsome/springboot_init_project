package com.jerry.springboot_project.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;
/**
 * MD5加密工具
 *
 * @author Jerry 2024.10.13
 */
public class MD5Util {

    public static String getSalt(){
        String salt= UUID.randomUUID().toString().toUpperCase();

        return salt;
    }


    public static String GetMD5Password(String password, String salt){

        for (int i = 0; i < 3; i++) {

            password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();

        }
        return password;
    }

}
