package com.weida.epuser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Dict {
    private Integer id;

    private String name;

    private String description;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private Date updateUser;

}