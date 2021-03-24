package com.weida.epmain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageStatus {
    private Integer id;

    private String messageId;

    private String type;

    private String topic;

    private Integer projectId;

    private Integer status;

    private Date createTime;

}