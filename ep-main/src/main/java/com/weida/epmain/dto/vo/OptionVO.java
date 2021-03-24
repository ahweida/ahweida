package com.weida.epmain.dto.vo;

import lombok.Data;

import java.util.List;

@Data
public class OptionVO {

    private String title;
    private Integer chartMainId;
    private Integer position;
    private Integer chartType;
    private Object  option;
    private List<TableVO> table;
}
