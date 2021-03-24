package com.weida.epmain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weida.epcommon.chart.bar.BarOption;
import com.weida.epcommon.chart.bar.BarSerie;
import com.weida.epcommon.chart.line.LineOption;
import com.weida.epcommon.chart.line.LineSerie;
import com.weida.epcommon.chart.pie.PieData;
import com.weida.epcommon.chart.pie.PieOption;
import com.weida.epcommon.chart.pie.PieSerie;
import com.weida.epmain.service.DapingService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DapingServiceImpl implements DapingService {




    @Override
    public Map<String, Object> getOption() {
        List list = Lists.newArrayList();

        String xdata[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        ArrayList<String> xAxisData = new ArrayList<>();
        for(String s : xdata){
            xAxisData.add(s);
        }
        List<BarSerie> series = new ArrayList<>();
        BarSerie serie = new BarSerie();
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
        serie.setData(data);
        series.add(serie);
        BarOption barOption = new BarOption(xAxisData, series);
//        list.add(JSON.toJSON(barOption));

        String xdataLine[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        ArrayList<String> xAxisDataLine = new ArrayList<>();
        for(String s : xdataLine){
            xAxisDataLine.add(s);
        }
        List<LineSerie> lineSeries = new ArrayList<>();
        LineSerie lineSerie = new LineSerie();
        List<Integer> lineData = new ArrayList<>();
        lineData.add(1);
        lineData.add(2);
        lineData.add(3);
        lineData.add(4);
        lineData.add(5);
        lineData.add(6);
        lineData.add(7);
        lineSerie.setData(lineData);
        lineSeries.add(lineSerie);
        LineOption lineOption = new LineOption(xAxisDataLine, lineSeries);
//        list.add(JSON.toJSON(lineOption));


//        String xdataPie[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
//        ArrayList<String> xAxisDataPie = new ArrayList<>();
//        for(String s : xdataPie){
//            xAxisDataLine.add(s);
//        }

        List<PieSerie> pieSeries = new ArrayList<>();
        PieSerie pieSerie = new PieSerie();
        List<PieData> pieDatas= new ArrayList<>();
        pieDatas.add(new PieData("百度", 100));
        pieDatas.add(new PieData("腾讯", 200));
        pieDatas.add(new PieData("阿里", 300));
        pieDatas.add(new PieData("华为", 400));
        pieDatas.add(new PieData("小米", 500));
        pieSerie.setData(pieDatas);
        pieSeries.add(pieSerie);
        PieOption pieOption = new PieOption( pieSeries);
        List<String> colors = Lists.newArrayList();
        colors.add("#ff0000");
        colors.add("#ff1111");
        colors.add("#ff2222");
        colors.add("#ff3333");
        pieOption.setColor(colors);
        list.add(JSON.parse(JSON.toJSONString(pieOption)));
        System.out.println(JSON.toJSON(pieOption));



        Map<String, Object> returnMap = Maps.newHashMap();
        returnMap.put("optionList", list);
        return returnMap;
    }




}
