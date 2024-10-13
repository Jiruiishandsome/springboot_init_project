package com.jerry.springboot_project.utils;


import javax.servlet.http.HttpServletRequest;
/**
 * 用户工具类
 *
 * @author Jerry 2024.10.13
 */
public class UserUtils {

    // 获取当前登录用户的ID
    public static Integer getCurrentUserId(HttpServletRequest request) {
        String Token = request.getHeader("Authorization");

        return JWT.getTokenId(Token);

    }
    public static String extractLastSixDigits(String phoneNumber) {
        // 检查手机号长度是否合法
        if (phoneNumber == null || phoneNumber.length() != 11) {
            throw new IllegalArgumentException("手机号格式不正确");
        }
        // 提取后六位
        return phoneNumber.substring(phoneNumber.length() - 6);
    }
}