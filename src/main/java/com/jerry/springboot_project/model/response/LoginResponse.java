package com.jerry.springboot_project.model.response;

import com.jerry.springboot_project.model.vo.UserVo;
import lombok.Data;

@Data
public class LoginResponse {

    private String token;

    private UserVo userVo;

}
