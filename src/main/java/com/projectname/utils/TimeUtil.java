package com.projectname.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    public static String getTime(){
        Date now =new Date();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

          String time = simpleDateFormat.format(now);
          return time;
        }

        /**
         * 获取精确时间
         * */
    public static String getPreciseTime(){
        Date now =new Date();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = simpleDateFormat.format(now);

        return time;
    }

    public static  String getAfter30min(){
        // 获取当前时间
        Date now = new Date();
        // 使用Calendar类进行操作
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(now);
        // 添加30分钟
        calendar.add(Calendar.MINUTE, 30);
        // 获取30分钟后的时间
        Date futureDate = calendar.getTime();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = simpleDateFormat.format(futureDate);

        return time;
    }

    public static long TimeDifferenceCalculator(String futureTimeStr){

        // 当前系统时间
        Date now = new Date();

        // 定义SimpleDateFormat以解析给定的时间字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 解析给定的时间字符串为Date对象
            Date futureTime = sdf.parse(futureTimeStr);

            // 检查未来时间是否在当前时间之后
            if (futureTime.after(now)) {
                // 计算时间差（毫秒）
                long diffInMillis = futureTime.getTime() - now.getTime();

//                    // 转换时间差为其他单位（如果需要）
//                    long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis);
//                    long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
//                    long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
//                    long diffInDays = diffInHours / 24;

                // 输出结果
//                    System.out.println("与当前时间相差的毫秒数: " + diffInMillis);
//                    System.out.println("与当前时间相差的秒数: " + diffInSeconds);
//                    System.out.println("与当前时间相差的分钟数: " + diffInMinutes);
//                    System.out.println("与当前时间相差的小时数: " + diffInHours);
//                    System.out.println("与当前时间相差的天数: " + diffInDays);
               return diffInMillis;
            } else {
                System.out.println("给定的时间在当前时间之前或相等。");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析时间字符串时出错。");
            return  -1;
        }
    }




}

