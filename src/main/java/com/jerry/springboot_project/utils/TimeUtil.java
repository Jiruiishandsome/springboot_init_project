package com.jerry.springboot_project.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
/**
 * 时间工具类
 *
 * @author Jerry 2024.10.13
 */
public class TimeUtil {
    // 日期格式化工具
    private static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat HOUR_MINUTE_FORMAT = new SimpleDateFormat("HH:mm", Locale.CHINA);
    private static final SimpleDateFormat WEEKDAY_FORMAT = new SimpleDateFormat("EEEE", Locale.CHINA);
    private static final SimpleDateFormat MONTH_DAY_FORMAT = new SimpleDateFormat("MM-dd", Locale.CHINA);
    private static final SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);


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

    public static String  get15MinTime(){

        LocalDateTime now = LocalDateTime.now();

        // 创建一个表示15分钟的Duration
        Duration fifteenMinutes = Duration.ofMinutes(15);

        // 计算15分钟后的时间
        LocalDateTime fifteenMinutesLater = now.plus(fifteenMinutes);

        // 创建一个DateTimeFormatter来格式化日期和时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化时间并打印
        String formattedTime = fifteenMinutesLater.format(formatter);

        return formattedTime;
    }



    // 获取当前日期
    private static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    // 获取当前日期的小时和分钟
    private static String getCurrentHourMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return sdf.format(getCurrentDate());
    }

    // 计算两个日期之间的天数差
    private static long daysBetween(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return Math.abs((cal2.get(Calendar.YEAR) * 365 + cal2.get(Calendar.DAY_OF_YEAR))
                - (cal1.get(Calendar.YEAR) * 365 + cal1.get(Calendar.DAY_OF_YEAR)));
    }

    // 计算两个日期之间的完整年数差（考虑闰年）
    private static long fullYearsBetween(Date fromDate, Date toDate) {
        Calendar fromCalendar = new GregorianCalendar();
        fromCalendar.setTime(fromDate);

        Calendar toCalendar = new GregorianCalendar();
        toCalendar.setTime(toDate);

        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);

        return toYear - fromYear;
    }

    // 转换日期格式
    public static String convertTimeFormat(Date date) {
        Date currentDate = getCurrentDate();

        // 计算时间差
        long daysDiff = daysBetween(date, currentDate);
        long yearsDiff = fullYearsBetween(date, currentDate);

        // 根据时间差判断显示格式
        if (daysDiff == 0) {
            // 当天时间，只显示小时和分钟
            return HOUR_MINUTE_FORMAT.format(date);
        } else if (daysDiff < 7) {
            // 7天以内，显示周几
            return WEEKDAY_FORMAT.format(date);
        } else if (daysDiff < 365) {
            // 一年以内，显示月和日
            return MONTH_DAY_FORMAT.format(date);
        } else {
            // 一年以上，显示年月日
            return YEAR_MONTH_DAY_FORMAT.format(date);
        }
    }

}

