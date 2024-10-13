package com.jerry.springboot_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.springboot_project.common.EmailEnum;
import com.jerry.springboot_project.common.ErrorCode;
import com.jerry.springboot_project.common.R;
import com.jerry.springboot_project.exception.BusinessException;
import com.jerry.springboot_project.mapper.UserMapper;
import com.jerry.springboot_project.model.email.EmailDetailRequest;
import com.jerry.springboot_project.model.entity.User;
import com.jerry.springboot_project.model.post.UserRegisterRequest;
import com.jerry.springboot_project.model.response.LoginResponse;
import com.jerry.springboot_project.model.vo.UserVo;
import com.jerry.springboot_project.service.UserService;
import com.jerry.springboot_project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户服务类（实现类）
 *
 *@author Jerry 2024.10.13
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class) //开启事务回滚

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserMapper userMapper;
    /***
     * 用户获取验证码
     * @param userRegisterRequest
     * @return
     */
    @Override
    public R sendCode(UserRegisterRequest userRegisterRequest) {

        String email = userRegisterRequest.getEmail();

        if(email.isEmpty()){
            throw  new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if(!EmailUtil.isValidEmail(email)){
            throw new BusinessException(ErrorCode.EMAIL_ERROR);
        }
//        获取验证码
        try {
            String random = RandomCodeUtil.random(6);

            EmailDetailRequest emailDetailRequest=new EmailDetailRequest();

            emailDetailRequest.setEmail(email);

            emailDetailRequest.setCode(random);

            String registerEmailDetail = EmailUtil.getRegisterEmailDetail(emailDetailRequest);

            EmailUtil.sendEmail(email, EmailEnum.SEND_EMAIL_CODE.getTitle(), registerEmailDetail);

            //code存入redis中，有效期5分钟
            redisUtil.set(email+"code",random,60*5);

            return R.success("验证码发送成功");
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"验证码发送失败");
        }
    }

    /***
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @Override
    public R userRegister(UserRegisterRequest userRegisterRequest) {

        String password = userRegisterRequest.getPassword();

        String username = userRegisterRequest.getUsername();

        String email = userRegisterRequest.getEmail();

        String code = userRegisterRequest.getCode();

        if(password.isEmpty()||username.isEmpty()||email.isEmpty()||code.isEmpty()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if(!EmailUtil.isValidEmail(email)){
            throw new BusinessException(ErrorCode.EMAIL_ERROR);
        }

        if(this.emailIsExist(email)){
            throw new BusinessException(ErrorCode.USER_EXIST_ERROR);
        }

//        验证code是否正确
        if(!this.verifyCode(code,email)){
            throw new BusinessException(ErrorCode.CODE_ERROR);
        }
//        获取密码进行加密

        String salt = MD5Util.getSalt();

        String Md5Password = MD5Util.GetMD5Password(password, salt);

        User user =new User();

        user.setEmail(email);

        user.setPassword(Md5Password);

        user.setUsername(username);

        user.setSalt(salt);

        user.setCreateTime(new Date());

        try {
            userMapper.insert(user);
            return R.success("注册成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 用户登录
     * @param userRegisterRequest
     * @return
     */
    @Override
    public R userLogin(UserRegisterRequest userRegisterRequest) {

        String password = userRegisterRequest.getPassword();

        String email = userRegisterRequest.getEmail();

        if(password.isEmpty()||email.isEmpty()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if(!EmailUtil.isValidEmail(email)){
            throw new BusinessException(ErrorCode.EMAIL_ERROR);
        }

        if(!this.emailIsExist(email)){
            throw new BusinessException(ErrorCode.USER_NOT_EXIST_ERROR);
        }

//        验证密码

        User userByEmail = userMapper.getUserByEmail(email);

        String salt = userByEmail.getSalt();

        String realPassword = userByEmail.getPassword();

        if(!realPassword.equals(MD5Util.GetMD5Password(password,salt))){
            throw new BusinessException(ErrorCode.USER_PASSWORD_ERROR);
        }

        String token = JWTUtil.getJWToken(userByEmail.getId());

        UserVo userVo=new UserVo();

        userVo.setId(userByEmail.getId());

        userVo.setRole(userByEmail.getRole());

        userVo.setAge(userByEmail.getAge());

        userVo.setAvatar(userByEmail.getAvatar());

        userVo.setPhone(userByEmail.getPhone());

        userVo.setSex(userByEmail.getSex());

        userVo.setEmail(userByEmail.getEmail());

        userVo.setUsername(userByEmail.getUsername());

        LoginResponse result=new LoginResponse();

        result.setToken(token);

        result.setUserVo(userVo);

        return R.success(result);

    }

    /***
     * 判断邮箱是否存在
     * @param email
     * @return
     */
    public boolean emailIsExist(String email){

       User user= userMapper.getUserByEmail(email);

       if(user!=null){
           return true;
       }else{
           return false;
       }
    }

    /***
     * 验证验证码是否正确
     * @param inputCode
     * @param email
     * @return
     */
    public boolean verifyCode(String inputCode,String email){

        String redisCode = (String)redisUtil.get(email + "code");

        if(redisCode==null){
            throw new BusinessException(ErrorCode.CODE_EXPIRED_ERROR);
        }
        if(inputCode.equals(redisCode)){
            return true;
        }else{
            return false;
        }
    }





}
