package com.jerry.springboot_project.controller;
import com.jerry.springboot_project.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * 上传文件类
 *
 *@author Jerry 2024.10.13
 */
@RestController
@Slf4j
public class UploadController {
    //设置上传文件的最大大小
    public static final int MAX_SIZE = 20 * 1024 * 1024;
    //设置文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/jpeg");
    }

    private static String BASE_URL="http://localhost:8080";
    /**
     * 上传图片
     *
     * @param request
     */

    @PostMapping("/upload_picture")


    public R upLoadPicture(HttpServletRequest request, @RequestParam("file") MultipartFile file) {


//MultipartFile是spring提供的类，可以接收所有的文件的类

        log.info(file.getContentType());

        if (file.isEmpty()) {
            return R.error("请选择文件");
        }

        if (file.getSize() > MAX_SIZE) {//file.getSize()获取接收文件的大小
            return R.error("文件大小超出最大限制");
        }
        if (!AVATAR_TYPE.contains(file.getContentType())) {//自定义接收文件的类型
            return R.error("文件类型不匹配");
        }

        String uploadPath = request.getSession().getServletContext().getRealPath("/upload/picture/");//获取上传文件的路径（获取项目中名为‘upload’的文件夹）

        log.info(uploadPath);

        File dir = new File(uploadPath);

        if (!dir.exists()) {
            dir.mkdirs();//若不存在，则创建该文件夹
        }

        String originalFilename = file.getOriginalFilename();//获取文件的真实文件名

        int index = originalFilename.lastIndexOf(".");//获取文件的后缀名‘.’的位置

        String substring = originalFilename.substring(index);//返回文件类型名 例如：.jpg

        String filename = UUID.randomUUID().toString().toUpperCase() + substring;//新创建的文件名

        File dest = new File(dir, filename);//创建一个空的文件

        try {

            file.transferTo(dest);

        } catch (IOException e) {

            System.out.println(e.getMessage());
            return R.error("文件存储出现异常");

        }
        String RealFilePath = BASE_URL + "/upload/picture/" + filename;
        Map<String, Object> data = new HashMap<>();
        data.put("image", RealFilePath);
        data.put("filename", filename);
        return R.success(data);//返回图片存储在服务器的地址
    }
}
