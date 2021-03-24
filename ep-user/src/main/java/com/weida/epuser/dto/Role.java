package com.weida.epuser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;

    private String name;

    private String systemCode;

    private String remark;

    private Integer daleteFlag;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}