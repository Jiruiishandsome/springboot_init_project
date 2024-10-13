package com.jerry.springboot_project.common;

public enum EmailEnum {

    SEND_EMAIL_CODE("您的验证码已发送 - 请查收");

    /**
     * 状态码
     */
    private final String title;

    /**
     * 信息
     */
    EmailEnum(String title) {
        this.title = title;

    }
    public String getTitle() {
        return title;
    }


    }
