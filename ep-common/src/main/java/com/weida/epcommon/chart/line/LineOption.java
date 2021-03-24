package com.weida.epcommon.chart.line;


import com.weida.epcommon.chart.common.Axis;
import com.weida.epcommon.chart.common.Legend;
import com.weida.epcommon.chart.common.Title;
import com.weida.epcommon.chart.common.Tooltip;
import lombok.Data;

import java.util.List;

@Data
public class LineOption {
    private Title title;
    private List<String> color;
    private Legend legend;
    private Axis xAxis;
    private Axis yAxis;
    private List<LineSerie> series;
    private Tooltip tooltip;

    public LineOption(){
    }


    public LineOption(List xAxisData, List<LineSerie> series){
        Axis xAxis = new Axis();
        xAxis.setType("category");
        xAxis.setData(xAxisData);
        this.xAxis = xAxis;
        Axis yAxis = new Axis();
        yAxis.setType("value");
        this.yAxis = yAxis;
        this.series = series;
    }




//    var lineOption ={
//        title: {
//            text: ''
//        },
//        color: ['#EE6363','#2db7f5', '#008B45', '#00ff00'],
//        legend: {
//            data: ['NOx入口', 'NOx出口', '氨水流量']
//        },
//        xAxis: {
//            type: 'category',
//            axisLine: {
//                lineStyle: {
//                    type: 'solid',
//                            color:'#fff',
//                            width:'2'
//                }
//            },
//            axisLabel:{
//                textStyle:{
//                    color:'white'
//                }
//            },
//            data: ['2020-09-29 00:00:00']
//        },
//        grid: {
//            top: 50,
//                    x: 45,
//                    x2: 30,
//                    y2: 30
//        },
//        yAxis: {
//            type: 'value',
//                    axisLine: {
//                lineStyle: {
//                    type: 'solid',
//                            color:'#fff',
//                            width:'2'
//                }
//            },
//            axisLabel:{
//                textStyle:{
//                    color:'white'
//                }
//            }
//        },
//        tooltip: {
//            trigger: 'axis'
//        },
//        dataZoom: [
//        {
//            type: 'inside'
//        }
//    ],
//        series: [
//        {
//            name: '预测值',
//            data: [820, 932, 901, 934, 1290, 1330, 1320],
//            type: 'line'
//        },
//        {
//            name: '真实值',
//                    data: [810, 931, 901, 734, 1090, 1230, 1360],
//            type: 'line'
//        }
//    ]
//    }
}
