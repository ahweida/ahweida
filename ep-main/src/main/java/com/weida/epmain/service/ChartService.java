package com.weida.epmain.service;



import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.Chart;
import com.weida.epmain.dto.vo.ChartVO;

import java.util.Map;

public interface ChartService {

    PageInfo<ChartVO> getChartPage(Map<String, Object> paramMap);

    void addChart(Chart chart);

    void editChart(Chart chart);

    void delChart(Integer id);

}
