package com.weida.epmain.mapper;

import com.weida.epmain.dto.ProjectMonitor;
import java.util.List;
import java.util.Map;

public interface ProjectMonitorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectMonitor record);

    int insertSelective(ProjectMonitor record);

    ProjectMonitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectMonitor record);

    int updateByPrimaryKey(ProjectMonitor record);

    List<ProjectMonitor> selectProjectMonitors(Map<String, Object> parameters);
}