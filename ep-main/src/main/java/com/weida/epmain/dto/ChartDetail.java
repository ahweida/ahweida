package com.weida.epmain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ChartDetail {
    private Integer id;

    @NotNull(message = "图表ID不能为空")
    private Integer chartMainId;


    private Integer dataDefineId;

    @NotBlank(message = "颜色不能为空")
    private String color;

    @NotBlank(message = "图例名称不能为空")
    private String chartName;

    @NotBlank(message = "单位不能为空")
    private String unit;

    @NotNull(message = "数据类型不能为空")
    private Integer dataType;

    private Double constantValue;

    @NotNull(message = "是否在图表中展示不能为空")
    private Integer chartShow;

    private Integer predictDataDefineId;

    private Date createTime;

    private Date updateTime;

}