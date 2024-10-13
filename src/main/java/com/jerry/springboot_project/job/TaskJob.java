package com.jerry.springboot_project.job;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 自动化任务
 *
 * @author Jerry 2024.10.13
 */
//@EnableScheduling
@Configuration
public class TaskJob {

    // 添加定时任务
    @Scheduled(cron = "0/30 * * * * ?") // cron表达式：每天6:00开始执行-每天的23:00结束，每1分钟执行
    //预约超时处理
    public void doTask(){


    }
}
