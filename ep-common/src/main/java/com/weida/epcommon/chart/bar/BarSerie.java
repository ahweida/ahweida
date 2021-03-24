package com.weida.epcommon.chart.bar;

import lombok.Data;

import java.util.List;

@Data
public class BarSerie {
    private List data;
    private String name;
    private String type = "bar";
    private boolean showBackground = false;
}
