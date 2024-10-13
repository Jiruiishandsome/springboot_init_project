package com.jerry.springboot_project.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户类
 *
 * @author Jerry 2024.10.13
 */
@Data
@TableName("user")
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String avatar;

    private Integer age;

    private String sex;

    private String role;

    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
