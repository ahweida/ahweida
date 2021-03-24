package com.weida.epmain.mapper;

import com.weida.epmain.dto.Chart;

import java.util.List;
import java.util.Map;

public interface ChartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chart record);

    int insertSelective(Chart record);

    Chart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chart record);

    int updateByPrimaryKey(Chart record);

    List<Chart> selectCharts(Map<String, Object> parameters);
}