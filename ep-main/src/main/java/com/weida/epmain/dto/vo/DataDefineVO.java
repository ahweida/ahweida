package com.weida.epmain.dto.vo;

import lombok.Data;

@Data
public class DataDefineVO {
    private Integer id;

    private String mysqlField;

    private String tablestoreField;

    private String dataType;

    private String dataName;

    private String dataUnit;

    private Integer decimalLength;

    private Integer collectionGroupId;

    private String collectionGroupName;

    private Integer projectId;

    private String projectName;


}