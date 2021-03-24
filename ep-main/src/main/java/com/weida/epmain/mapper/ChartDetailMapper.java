package com.weida.epmain.mapper;

import com.weida.epmain.dto.ChartDetail;

import java.util.List;
import java.util.Map;

public interface ChartDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChartDetail record);

    int insertSelective(ChartDetail record);

    ChartDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChartDetail record);

    int updateByPrimaryKey(ChartDetail record);

    List<ChartDetail> selectChartsDetails(Map<String, Object> parameters);
}