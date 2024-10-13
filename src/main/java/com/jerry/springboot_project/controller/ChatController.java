package com.jerry.springboot_project.controller;

import com.jerry.springboot_project.common.R;
import com.jerry.springboot_project.model.entity.Message;
import com.jerry.springboot_project.server.WebSocketServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * WebSocket发送消息接口
 *
 * @author Jerry 2024.10.13
 */

@RestController
public class ChatController {

    @RequestMapping("/chat")
    public R demo(@RequestBody Message message) {

        try {
            //给前端web推送数据
            WebSocketServer.sendInfo(message);
            return R.success("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("发送失败");
        }


    }

}
