package com.weida.epuser.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Dept {
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    private String name;

    @NotNull(message = "上级部门不能为空")
    private Integer parentId;

    private int deleteFlag;

    @NotNull(message = "部门序列不能为空")
    private Integer orderNum;

    private String remark;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}