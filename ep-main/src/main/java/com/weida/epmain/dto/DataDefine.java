package com.weida.epmain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DataDefine {
    private Integer id;

    private String mysqlField;

    @NotBlank(message = "tablestore字段名不能为空")
    private String tablestoreField;

    @NotBlank(message = "数据类型不能为空")
    private String dataType;

    @NotBlank(message = "数据名称不能为空")
    private String dataName;

    @NotBlank(message = "数据单位不能为空")
    private String dataUnit;

    @NotNull(message = "小数位数不能为空")
    private Integer decimalLength;

    @NotNull(message = "采集组不能为空")
    private Integer collectionGroupId;

    private Integer projectId;

}