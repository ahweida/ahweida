package com.weida.epcommon.jwt;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/11 17:18
 * @Desc: 用户信息实体
 */
@Data
@Accessors(chain = true)
public class UserInfo {
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
