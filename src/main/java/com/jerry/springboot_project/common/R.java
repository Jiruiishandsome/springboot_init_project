package com.jerry.springboot_project.common;

import lombok.Data;
/**
 * 通用返回类
 *
 *@author Jerry 2024.10.13
 */
@Data
public class R<T>{
    private Integer code;
    private String message;
    private Object data;

    public R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> R<T> success(T object){
        R r=  new R<>();
        r.code=200;
        r.data=object;
        r.message="成功";
        return r;
    }
    public static<T> R<T> error(String message){
        R r=new R<>();

        r.code=500;
        r.message=message;
        return r;
    }
    public static<T> R<T> error(Integer code,String message){
        R r=new R<>();
        r.code=code;
        r.message=message;
        return r;
    }
    public R() {
    }


}
