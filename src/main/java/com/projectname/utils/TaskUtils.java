package com.projectname.utils;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableScheduling
@Configuration

public class TaskUtils {

    // 添加定时任务
    @Scheduled(cron = "0 0/1 6-23 * * ?") // cron表达式：每天6:00开始执行-每天的23:00结束，每1分钟执行
    //预约超时处理
    public void doTask(){



    }


}
