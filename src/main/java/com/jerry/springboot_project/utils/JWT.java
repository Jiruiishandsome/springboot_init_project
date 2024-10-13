package com.jerry.springboot_project.utils;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
/**
 * JWT
 *
 * @author Jerry 2024.10.13
 */
public class JWT {
    // 任意字符串
    @Value("${app.JwtSign}")
    private static String SING ;

    private static String token;

    // get and set
    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        JWT.token = token;
    }

    // 生成用户token
    public static String getJWToken(Integer id){

        Calendar instance = Calendar.getInstance();
        // 设置过期时间,这里设置的是一天
        instance.add(Calendar.DATE,30);

        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();

        // 指定标识字段
        builder.withClaim("userId", id);

        // 指定过期时间
        token = builder.withExpiresAt(instance.getTime())
                // 指定生成算法及签名
                .sign(Algorithm.HMAC256(SING));

        return token;
    }

    // 验证token,返回true或false
    public static boolean verify(String token){
        try {
            com.auth0.jwt.JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 验证token,正确通过,否则抛出异常
    public static DecodedJWT verifyToken(String lawyerToken){
        return com.auth0.jwt.JWT.require(Algorithm.HMAC256(SING)).build().verify(lawyerToken);
    }

    // 从token中获取用户id
    public static int getTokenId(String token){
        DecodedJWT verify = com.auth0.jwt.JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify.getClaim("userId").asInt();
    }
}