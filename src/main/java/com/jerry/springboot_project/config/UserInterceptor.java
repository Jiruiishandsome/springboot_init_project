package com.jerry.springboot_project.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import com.jerry.springboot_project.bean.BeanUtils;
import com.jerry.springboot_project.utils.JWTUtil;
import com.jerry.springboot_project.utils.RedisUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Token验证类
 *
 * @author Jerry 2024.10.13
 */
public class UserInterceptor implements HandlerInterceptor {

    private RedisUtil redisUtil= BeanUtils.getBean(RedisUtil.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Map<String, Object> map = new HashMap<>();

        String Token = request.getHeader("Authorization");

        // 捕获刚刚JWT中抛出的异常,并封装对应的返回信息
        try {
            JWTUtil.verifyToken(Token);
            Integer userId= JWTUtil.getTokenId(Token);
            if(redisUtil.get(userId.toString()) != null&&Token!=null) {
                String findToken = redisUtil.get(userId.toString()).toString();
                if (!Token.equals(findToken)) {
                    map.put("message", "无效身份信息");
                    map.put("code", 401);
                    JSONObject json = new JSONObject(map);
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.print(json);
                    writer.flush();
                    writer.close();
                    return false;
                }
            }
            redisUtil.set(userId.toString()+"online",1,60*15);
            return true;
        }catch (SignatureVerificationException e){
            map.put("message", "无效签名");
        }catch (TokenExpiredException e){
            map.put("message", "已过期");
        }catch (AlgorithmMismatchException e){
            map.put("message", "算法不一致");
        }catch (Exception e){
            map.put("message", "无效身份信息");
            e.printStackTrace();
        }
        // 封装返回值
        map.put("code", 401);
        JSONObject json = new JSONObject(map);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
        return false;
    }
}