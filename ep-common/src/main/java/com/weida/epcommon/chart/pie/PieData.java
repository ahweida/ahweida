package com.weida.epcommon.chart.pie;

import lombok.Data;

@Data
public class PieData {
    private Object value;
    private String name;

    public PieData(String name, Object value) {
        this.value = value;
        this.name = name;
    }
}
