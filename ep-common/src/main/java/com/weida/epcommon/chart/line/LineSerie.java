package com.weida.epcommon.chart.line;

import lombok.Data;

import java.util.List;

@Data
public class LineSerie {
    private List data;
    private String name;
    private String type = "line";
    private boolean showBackground = true;
}
