package com.weida.epcommon.chart.common;

import lombok.Data;

import java.util.List;

@Data
public class Axis {
    private  String type;
    private  String name;
    private List data;
    private AxisLine axisLine;
    private AxisLabel axisLabel;
    private SplitLine splitLine;
}
