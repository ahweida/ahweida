package com.weida.epmain.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.ProjectMonitor;
import com.weida.epmain.dto.vo.ProjectMonitorVO;
import com.weida.epmain.mapper.DataDefineMapper;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.mapper.ProjectMonitorMapper;
import com.weida.epmain.service.ProjectMonitorService;
import com.weida.epmain.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/8 10:12
 * @Desc: TODO
 */
@Service
public class ProjectMonitorServiceImpl implements ProjectMonitorService {

    @Resource
    private ProjectMonitorMapper projectMonitorMapper;
    @Resource
    private DataDefineMapper dataDefineMapper;
    @Resource
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectMonitorVO> getProjectMonitors(Integer id) {
        Map<String,Object> parameters = Maps.newHashMap();
        parameters.put("projectId", id);
        List<ProjectMonitor> projectMonitors = projectMonitorMapper.selectProjectMonitors(parameters);
        Project project = projectMapper.selectByPrimaryKey(id);
        List<ProjectMonitorVO> monitorVOS = Lists.newArrayList();
        projectMonitors.forEach(a -> {
            ProjectMonitorVO monitorVO = new ProjectMonitorVO();
            BeanUtils.copyProperties(a,monitorVO);
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(a.getDataDefineId());
            monitorVO.setDataName(dataDefine.getDataName());
            monitorVO.setProjectName(project.getProjectName());
            monitorVOS.add(monitorVO);
        });
        return monitorVOS;
    }

    @Override
    public void editProjectMonitor(ProjectMonitor monitor) {
        projectMonitorMapper.updateByPrimaryKeySelective(monitor);
    }

    @Override
    public void addProjectMonitor(ProjectMonitor monitor) {
        projectMonitorMapper.insert(monitor);
    }

    @Override
    public void delProjectMonitor(Integer id) {
        projectMonitorMapper.deleteByPrimaryKey(id);
    }
}
