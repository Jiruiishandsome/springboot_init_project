package com.jerry.springboot_project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jerry.springboot_project.common.R;
import com.jerry.springboot_project.model.entity.User;
import com.jerry.springboot_project.model.post.UserRegisterRequest;
/**
 * 用户服务类（接口）
 *
 *@author Jerry 2024.10.13
 */
public interface UserService extends IService<User> {

    /***
     * 发送验证码
     * @param userRegisterRequest
     * @return
     */
    R sendCode(UserRegisterRequest userRegisterRequest);

    /***
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    R userRegister(UserRegisterRequest userRegisterRequest);

    /***
     * 用户登录
     *
     * @param userRegisterRequest
     * @return
     */
    R userLogin(UserRegisterRequest userRegisterRequest);
}
