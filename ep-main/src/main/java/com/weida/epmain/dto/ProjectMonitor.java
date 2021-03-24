package com.weida.epmain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProjectMonitor {
    private Integer id;

    @NotNull(message = "项目不能为空")
    private Integer projectId;
    @NotNull(message = "定义数据不能为空")
    private Integer dataDefineId;
    @NotNull(message = "等级不能为空")
    private Integer level;
    @NotNull(message = "正常值下限不能为空")
    private Double normalMin;
    @NotNull(message = "正常值上限不能为空")
    private Double normalMax;
    @NotNull(message = "警戒值下限不能为空")
    private Double warnMin;
    @NotNull(message = "警戒值上限不能为空")
    private Double warnMax;
    @NotNull(message = "超标值下限不能为空")
    private Double beyondMin;
    @NotNull(message = "超标值上限不能为空")
    private Double beyondMax;

    private Integer xcoord;

    private Integer ycoord;

}