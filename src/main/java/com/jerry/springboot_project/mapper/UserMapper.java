package com.jerry.springboot_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.springboot_project.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户mapper
 *
 *@author Jerry 2024.10.13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    /***
     * 根据邮箱地址查找用户
     * @param email
     * @return
     */
    @Select("select * from user where email=#{email}")
    User getUserByEmail(String email);
}
