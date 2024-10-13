package com.jerry.springboot_project.controller;

import com.jerry.springboot_project.common.R;
import com.jerry.springboot_project.model.post.UserRegisterRequest;
import com.jerry.springboot_project.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户接口
 *
 *@author Jerry 2024.10.13
 */
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 发送验证码
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/sendCode")
    public R sendCode(@RequestBody UserRegisterRequest userRegisterRequest){

        return userService.sendCode(userRegisterRequest);
    }

    /***
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public R userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.userRegister(userRegisterRequest);
    }

    /***
     * 用户登录
     *
     * @param userRegisterRequest
     * @return
     */

    @PostMapping("/login")
    public R userLogin(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.userLogin(userRegisterRequest);
    }
}
