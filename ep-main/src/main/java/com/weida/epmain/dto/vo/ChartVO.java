package com.weida.epmain.dto.vo;

import lombok.Data;

@Data
public class ChartVO {
    private Integer id;

    private Integer projectId;

    private String projectName;

    private String chartName;

    private Integer chartType;

    private Integer hour;

    private Integer position;

    private Integer groupType;

    private Integer status;

}