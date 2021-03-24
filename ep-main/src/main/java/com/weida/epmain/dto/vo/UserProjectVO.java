package com.weida.epmain.dto.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/13 13:58
 * @Desc: TODO
 */
@Data
public class UserProjectVO {
    private Integer id;

    private Integer userId;

    private Integer projectId;

    private Integer readFlag;

    private Integer writeFlag;

    private Date createTime;

    private Date updateTime;
}
