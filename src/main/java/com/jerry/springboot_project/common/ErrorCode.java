package com.jerry.springboot_project.common;

/**
 * 自定义错误码
 *
 * @author Jerry 2024.10.13
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),
    USER_EXIST_ERROR(50002, "用户已存在"),
    CODE_EXPIRED_ERROR(50003, "验证码已过期"),
    CODE_ERROR(50004, "验证码错误"),
    EMAIL_ERROR(50004, "邮箱格式错误"),
    USER_NOT_EXIST_ERROR(50005, "用户不存在"),
    USER_PASSWORD_ERROR(50006, "账号或者密码错误");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
