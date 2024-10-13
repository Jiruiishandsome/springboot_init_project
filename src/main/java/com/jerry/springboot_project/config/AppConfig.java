package com.jerry.springboot_project.config;

import com.sun.scenario.Settings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * 用户自定义全局变量配置类
 *
 * @author Jerry 2024.10.13
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {

//    app:
//    uploadBaseUrl: "http://localhost:8080" #上传文件的地址
//    emailHost: "smtp.qq.com" #邮件服务器主机名
//    emailAccount : "xxx@qq.com" #发件人邮箱
//    emailPassword : "xxxxxxxxxxx" #发件人邮箱密码（授权码）
//    JwtSign : "xxxx@Jerry" #Jwt签名

    private String uploadBaseUrl;

    private String emailHost;

    private String emailPassword;

    private String JwtSign;


}
