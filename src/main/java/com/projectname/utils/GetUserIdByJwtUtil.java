package com.projectname.utils;

import javax.servlet.http.HttpServletRequest;

public class GetUserIdByJwtUtil {

    public static Integer getUserId(HttpServletRequest request){

        String Token = request.getHeader("Authorization");

        Integer userId = JWT.getTokenId(Token);

        return userId;
    }
}
