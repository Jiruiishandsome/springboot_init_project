package com.jerry.springboot_project.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {

    private Long id;

    private String username;

    private String avatar;

    private Integer age;

    private String sex;

    private String role;

    private String email;

    private String phone;

}
