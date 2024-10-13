package com.jerry.springboot_project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * message类
 *
 * @author Jerry 2024.10.13
 */
@Data
@TableName("message")
public class Message implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Integer senderId;

    private Integer receiveId;

    private String content;

    private String messageType;//image ,text, voice

    private String type; //order single  group

    private Date sendTime;

    private Integer isSystem;

    private Integer viewed;

    private Integer self;

    private String avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
