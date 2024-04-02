package com.projectname.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;

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
