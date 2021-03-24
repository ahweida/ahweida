package com.weida.epuser.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class User {
    private Integer id;

    private String name;

    private String account;

    private String password;

    private String email;

    private String phone;

    private Integer status;

    private Integer deptId;

    private Integer postId;

    private String remark;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}