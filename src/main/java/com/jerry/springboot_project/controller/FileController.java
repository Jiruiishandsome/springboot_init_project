package com.jerry.springboot_project.controller;

import com.jerry.springboot_project.config.FileStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
public class FileController {
    @Autowired
    private FileStorageConfig fileStorageConfig;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // 生成唯一文件名
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

        // 保存文件
        try {
            Path targetPath = fileStorageConfig.getUploadPath().resolve(uniqueFileName);
            file.transferTo(targetPath);
        } catch (IOException ex) {
            throw new RuntimeException("文件保存失败: " + uniqueFileName, ex);
        }

        return uniqueFileName;
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = fileStorageConfig.getUploadPath().resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("文件未找到: " + fileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("下载失败: " + fileName, ex);
        }
    }
}
