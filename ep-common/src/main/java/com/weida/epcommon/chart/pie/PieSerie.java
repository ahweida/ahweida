package com.weida.epcommon.chart.pie;

import lombok.Data;

import java.util.List;

@Data
public class PieSerie {
    private List data;
    private String name ="";
    private String type = "pie";
    private String radius = "55%";
}
