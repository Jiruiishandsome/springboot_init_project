package com.jerry.springboot_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 主程序
 *
 *@author Jerry 2024.10.13
 */

//开启自动任务
//@EnableScheduling
//    扫描bean文件
//@ServletComponentScan("com.jerry.springboot_project.bean")

@SpringBootApplication
//@EnableConfigurationProperties(AppConfig.class)
@ServletComponentScan({"com.jerry.springboot_project.bean"})
@Slf4j
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);

    }

}
