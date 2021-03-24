package com.weida.epuser.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Post {
    private Integer id;

    @NotBlank(message = "岗位名称不能为空")
    private String name;

    @NotNull(message = "岗位序列不能为空")
    private Integer orderNum;

    private String remark;

    private Integer deleteFlag;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}