package com.projectname.utils;

import java.util.Random;

public class RandomCodeUtil {
    /**
     * 生成的验证码
     * @param index 生成验证码的长度
     * @return
     */
    public static String random(Integer index){
        String code = "";
        Random rd=new Random();
        for (int i = 0; i < index; i++) {
            int r = rd.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
        }
        System.out.println(code);
        return code;
    }
}
