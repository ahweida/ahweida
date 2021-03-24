package com.weida.epcommon.chart.common;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @desc: 创建option类的工具类
 * @author: zhaofei
 * @date: 2021/1/7 10:58
 */
@Data
public class Options {

    public static Option newOption(){
        return  new Option();
    }

    public static Option getDefaultOption(){
        Option option = new  Option();

        //设置折线图上方的图标
        Legend legend = new Legend();
        TextStyle textStyle = new TextStyle();
        legend.setTextStyle(textStyle);
        List<String> legendDatas = Lists.newArrayList();
        legend.setData(legendDatas);
        option.setLegend(legend);
        //设置折线颜色
        List<String> colors = Lists.newArrayList();
        option.setColor(colors);
        //初始化折线图X坐标值数据
        List<String> xAxisData = Lists.newArrayList();
        //x坐标
        Axis xAxis = new Axis();
        xAxis.setType("category");  //x坐标为类别
        LineStyle lineStyle = new LineStyle();
        AxisLine axisLine = new AxisLine();
        axisLine.setLineStyle(lineStyle);
        xAxis.setAxisLine(axisLine);
        AxisLabel axisLabel = new AxisLabel();
        TextStyle textStyle1 = new TextStyle();
        textStyle1.setColor("#2EC7C9");
        axisLabel.setTextStyle(textStyle1);
        xAxis.setAxisLabel(axisLabel);
        //y坐标
        Axis yAxis = new Axis();
        yAxis.setType("value");     //y坐标为值
        yAxis.setName("指标值");
        yAxis.setAxisLine(axisLine);
        yAxis.setAxisLabel(axisLabel);
        option.setYAxis(yAxis);
        //设置鼠标悬浮显示详情
        Tooltip tooltip = new Tooltip();
        //tooltip.setTrigger("axis");
        option.setTooltip(tooltip);
        return  option;
    }

    /**
     * @Desc
     * @param legendDatas
     * @param colors
     * @param xAxisData
     * @return
     */
    public static Option getDefaultOption(List<String> legendDatas, List<String> colors, List<String> xAxisData){
        Option option = new  Option();
        //设置折线图上方的图标
        Legend legend = new Legend();
        TextStyle textStyle = new TextStyle();
        legend.setTextStyle(textStyle);
        legend.setData(legendDatas);
        option.setLegend(legend);
        //设置折线颜色
        option.setColor(colors);
        //初始化折线图X坐标值数据
        //x坐标
        Axis xAxis = new Axis();
        xAxis.setType("category");  //x坐标为类别
        LineStyle lineStyle = new LineStyle();
        AxisLine axisLine = new AxisLine();
        axisLine.setLineStyle(lineStyle);
        xAxis.setAxisLine(axisLine);
        AxisLabel axisLabel = new AxisLabel();
        TextStyle textStyle1 = new TextStyle();
        textStyle1.setColor("#2EC7C9");
        axisLabel.setTextStyle(textStyle1);
        //默认设置很坐标倾斜
//        axisLabel.setInterval(5);
//        axisLabel.setRotate(20);
        xAxis.setAxisLabel(axisLabel);
        xAxis.setData(xAxisData);
        option.setXAxis(xAxis);
        //y坐标
        Axis yAxis = new Axis();
        yAxis.setType("value");     //y坐标为值
        yAxis.setName("指标值");
        yAxis.setAxisLine(axisLine);
        yAxis.setAxisLabel(axisLabel);
        SplitLine splitLine = new SplitLine();
        LineStyle splitLineStyle = new LineStyle();
        splitLineStyle.setColor("rgba(219,225,255,0.2)");
        splitLine.setLineStyle(splitLineStyle);
        yAxis.setSplitLine(splitLine);
        option.setYAxis(yAxis);
        //设置鼠标悬浮显示详情
        Tooltip tooltip = new Tooltip();
        //tooltip.setTrigger("axis");
        option.setTooltip(tooltip);
        //设置鼠标滚动放大缩小功能
        List<DataZoom> dataZooms = Lists.newArrayList();
        DataZoom dataZoom = new DataZoom();
        dataZoom.setType("inside");
        dataZooms.add(dataZoom);
        option.setDataZoom(dataZooms);
        return  option;
    }

    public static List<String> getDefaultColors(){
        List<String> list = Lists.newArrayList();
        list.add("#FF4500");
        list.add("#EEDD82");
        list.add("#00F5FF");
        list.add("#0000FF");
        list.add("#8B0A50");
        list.add("#FFFFFF");
        return  list;
    }

}
