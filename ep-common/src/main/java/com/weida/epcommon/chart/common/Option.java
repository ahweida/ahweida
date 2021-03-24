package com.weida.epcommon.chart.common;

import lombok.Data;

import java.util.List;

@Data
public class Option {
    private Title title;
    private List<String> color;
    private Legend legend;
    private Axis xAxis;
    private Axis yAxis;
    private List<Serie> series;
    private Tooltip tooltip;
    private List<DataZoom> dataZoom;

    public Option(){
    }
}
