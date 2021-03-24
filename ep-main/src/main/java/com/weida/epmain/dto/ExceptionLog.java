package com.weida.epmain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionLog {
    private Integer id;

    private Integer projectId;

    private Date createTime;

    private String description;
}