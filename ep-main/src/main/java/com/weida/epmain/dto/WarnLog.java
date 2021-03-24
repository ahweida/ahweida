package com.weida.epmain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WarnLog {
    private Integer id;

    private Integer projectId;

    private Integer dataDefineId;

    private Date collectTime;

    private Double dataValue;

    private Integer warnType;

    private Double normalMin;

    private Double normalMax;

}