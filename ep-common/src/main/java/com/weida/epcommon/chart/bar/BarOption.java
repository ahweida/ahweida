package com.weida.epcommon.chart.bar;

import com.weida.epcommon.chart.common.Axis;
import com.weida.epcommon.chart.common.Legend;
import com.weida.epcommon.chart.common.Title;
import com.weida.epcommon.chart.common.Tooltip;
import lombok.Data;

import java.util.List;

@Data
public class BarOption {
    private Title title ;
    private List<String> color;
    private Axis xAxis ;
    private Axis yAxis ;
    private List<BarSerie> series;
    private Legend legend;
    private Tooltip tooltip;

    public BarOption(){
    }

    public BarOption(List xAxisData, List<BarSerie> series){
        Axis xAxis = new Axis();
        xAxis.setType("category");
        xAxis.setData(xAxisData);
        this.xAxis = xAxis;
        Axis yAxis = new Axis();
        yAxis.setType("value");
        this.yAxis = yAxis;
        this.series = series;
    }

//    option =  {
//        tooltip: {
//            trigger: 'axis',
//             axisPointer: {
//                type: 'shadow'
//            }
//        },
//        grid: {
//            left: 50,
//                    top:10
//        },
//        legend: {
//            data: ['报警值', '目标值', '当前值']
//        },
//        xAxis: {
//            type: 'value',
//            name: '',
//            axisLine: {
//                  lineStyle: {
//                      type: 'solid',
//                      color:'#fff',
//                      width:'2'
//                  }
//            },
//            axisLabel:{
//                textStyle:{
//                    color:'white'
//                }
//            }
//
//        },
//        yAxis: {
//            type: '',
//            inverse: true,
//            axisLine: {
//                lineStyle: {
//                  type: 'solid',
//                  color:'#fff',
//                  width:'2'
//                 }
//            },
//            axisLabel:{
//                textStyle:{
//                    color:'white'
//                }
//            },
//            data: ['报警值', '目标值', '当前值']
//
//        },
//        series: [
//        {
//            type: 'bar',
//                    itemStyle: {
//            normal: {
//                //这里是重点
//                color: function(params) {
//                    //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
//                    var colorList = ['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83', '#ca8622'];
//                    return colorList[params.dataIndex]
//                }
//            }
//        },
//            data: [30, 25, 25]
//        }
//    ]
//    }




}
