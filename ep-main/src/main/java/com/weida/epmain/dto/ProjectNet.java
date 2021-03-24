package com.weida.epmain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ProjectNet {
    private Integer id;

    @NotBlank(message = "类型不能为空")
    private String name;

    @NotNull(message = "入参个数不能为空")
    private Integer inNum;

    @NotNull(message = "出参个数不能为空")
    private Integer outNum;

    @NotNull(message = "隐藏层个数不能为空")
    private Integer hiddenNum;

    @NotNull(message = "预测时间不能为空")
    private Integer predictTime;

    @NotNull(message = "训练数据范围不能为空")
    private Integer trainDataLimits;

    @NotNull(message = "迭代次数不能为空")
    private Integer iteratorNum;

    @NotNull(message = "文件路径不能为空")
    private String fileName;

    private String version;

    @NotNull(message = "项目不能为空")
    private Integer projectId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}