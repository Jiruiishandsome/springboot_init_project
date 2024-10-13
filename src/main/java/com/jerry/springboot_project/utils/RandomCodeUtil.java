package com.jerry.springboot_project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
/**
 * 随机生成编码工具类
 *
 * @author Jerry 2024.10.13
 */
public class RandomCodeUtil {
    // 序列号生成器，用于在同一毫秒内生成多个ID时保持唯一性
    private static final AtomicLong sequence = new AtomicLong(0);
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
    public static String generateOrderId() {
        // 定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获取当前时间
        String currentTime = sdf.format(new Date());

        // 创建一个随机数生成器
        Random random = new Random();
        // 生成一个4位的随机数
        int randomNum = random.nextInt(9000) + 1000; // 确保是4位数

        // 拼接订单号
        return currentTime + String.format("%04d", randomNum);
    }


}
