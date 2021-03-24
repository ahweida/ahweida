package com.weida.epmain.dto;

import lombok.Data;

@Data
public class DynamicControl {
    private Integer id;

    private Integer projectId;

    private Integer groupId;

    private Integer outMax;

    private Integer outMin;

    private Double ratio;

    private Double constant;

    private Double directPart;

    private Double addMax;

    private Double addMin;

}