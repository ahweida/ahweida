package com.weida.epuser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DictItem {
    private Integer id;

    private Integer dictId;

    private String dictText;

    private String dictValue;

    private Integer orderNum;

    private String description;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}