package com.weida.epcommon.chart.common;

import lombok.Data;

/**
 * @Auther: zhaof
 * @Date: 2021/1/26 8:59
 * @Desc: TODO
 */
@Data
public class Label {

    //默认显示数值
    private Boolean show = true;
    //默认数值显示在柱子上方
    private String position = "top";
    private TextStyle textStyle;
}
