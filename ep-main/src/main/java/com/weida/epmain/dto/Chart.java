package com.weida.epmain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Chart {
    private Integer id;

    @NotNull(message = "项目不能为空")
    private Integer projectId;

    @NotBlank(message = "图表名称不能为空")
    private String chartName;

    @NotNull(message = "图表类型不能为空")
    private Integer chartType;

    @NotNull(message = "时间跨度不能为空")
    private Integer hour;

    @NotNull(message = "位置不能为空")
    private Integer position;

    @NotNull(message = "分组类型不能为空")
    private Integer groupType;

    @NotNull(message = "图表状态不能为空")
    private Integer status;

}