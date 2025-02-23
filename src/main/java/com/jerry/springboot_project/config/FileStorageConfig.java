package com.jerry.springboot_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileStorageConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // 获取上传路径
    public Path getUploadPath() {
        return Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    // 初始化时创建目录
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(getUploadPath());
        } catch (Exception ex) {
            throw new RuntimeException("无法创建上传目录", ex);
        }
    }
}
