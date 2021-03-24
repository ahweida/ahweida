package com.weida.epmain.service.impl;

import com.weida.epmain.dto.ChartDetail;
import com.weida.epmain.mapper.ChartDetailMapper;
import com.weida.epmain.service.ChartDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ChartDetailServiceImpl implements ChartDetailService {

    @Resource
    private ChartDetailMapper chartDetailMapper;

    @Override
    public List<ChartDetail> getChartDetails(Map<String, Object> paramMap) {
        return chartDetailMapper.selectChartsDetails(paramMap);
    }

    @Override
    public void addChartDetail(ChartDetail chart) {
        chartDetailMapper.insert(chart);
    }

    @Override
    public void editChartDetail(ChartDetail chart) {
        chartDetailMapper.updateByPrimaryKey(chart);
    }

    @Override
    public void delChartDetail(Integer id) {
        chartDetailMapper.deleteByPrimaryKey(id);
    }


}
