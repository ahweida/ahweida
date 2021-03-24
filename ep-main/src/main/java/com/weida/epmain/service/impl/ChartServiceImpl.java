package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.Chart;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.ChartVO;
import com.weida.epmain.dto.vo.DataDefineVO;
import com.weida.epmain.mapper.ChartMapper;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.service.ChartService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements ChartService {

    @Resource
    private ChartMapper chartMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageInfo<ChartVO> getChartPage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<Chart> dataDefines = chartMapper.selectCharts(paramMap);
        List<ChartVO> list = Lists.newArrayList();
        Map<Integer, String> projectMap = Maps.newHashMap();
        for (Chart chart : dataDefines){
            ChartVO chartVO = new ChartVO();
            BeanUtils.copyProperties(chart,chartVO);
            if(projectMap.get(chartVO.getProjectId()) == null){
                Project project = projectMapper.selectByPrimaryKey(chartVO.getProjectId());
                projectMap.put(chartVO.getProjectId(), project.getProjectName());
            }
            chartVO.setProjectName(projectMap.get(chartVO.getProjectId()));
            list.add(chartVO);
        }
        return new PageInfo<ChartVO>(list);
    }

    @Override
    public void addChart(Chart chart) {
        chartMapper.insert(chart);
    }

    @Override
    public void editChart(Chart chart) {
        chartMapper.updateByPrimaryKeySelective(chart);
    }

    @Override
    public void delChart(Integer id) {
        chartMapper.deleteByPrimaryKey(id);
    }
}
