package com.jerry.springboot_project.controller;

import com.jerry.springboot_project.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final AppConfig appConfig;

    @Autowired
    public AppController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/info")
    public String getAppInfo() {
        return "App getEmailHost: " + appConfig.getEmailHost() + "\n" +
                "App getJwtSign: " + appConfig.getJwtSign() + "\n" +
                "App getUploadBaseUrl: " + appConfig.getUploadBaseUrl() + "\n" +
                "App getEmailPassword: " + appConfig.getEmailPassword();

    }
}
