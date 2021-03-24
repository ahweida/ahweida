package com.weida.epcommon.chart.common;

import lombok.Data;

import java.util.List;

@Data
public class Serie {
    private List data;
    private String name;
    private String type = "line";
    private boolean showBackground = false;
    private String symbol = "none";//折线图默认没有小圆圈
    private ItemStyle itemStyle;
}
