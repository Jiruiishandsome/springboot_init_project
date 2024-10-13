package com.jerry.springboot_project.model.post;

import lombok.Data;

/**
 * 用户注册使用的request参数
 *
 *@author Jerry 2024.10.13
 */
@Data
public class UserRegisterRequest {

    private String username;

    private String password;

    private String code;

    private String email;
}
