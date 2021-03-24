package com.weida.epmain.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectNetVO {
    private Integer id;

    private String name;

    private Integer inNum;

    private Integer outNum;

    private Integer hiddenNum;

    private Integer predictTime;

    private Integer trainDataLimits;

    private Integer iteratorNum;

    private String fileName;

    private String version;

    private Integer projectId;

    private String projectName;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}