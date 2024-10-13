package com.jerry.springboot_project.model.email;

import lombok.Data;

@Data
public class EmailDetailRequest {

    private String username;

    private String code;

    private String email;

}
