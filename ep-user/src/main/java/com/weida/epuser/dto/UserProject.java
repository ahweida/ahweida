package com.weida.epuser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserProject {
    private Integer id;

    private Integer userId;

    private Integer projectId;

    private Integer readFlag;

    private Integer writeFlag;

    private Date createTime;

    private Date updateTime;

}