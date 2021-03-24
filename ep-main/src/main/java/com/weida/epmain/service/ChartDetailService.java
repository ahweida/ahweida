package com.weida.epmain.service;

import com.weida.epmain.dto.ChartDetail;

import java.util.List;
import java.util.Map;

public interface ChartDetailService {

    List<ChartDetail> getChartDetails(Map<String, Object> paramMap);

    void addChartDetail(ChartDetail chart);

    void editChartDetail(ChartDetail chart);

    void delChartDetail(Integer id);

}
