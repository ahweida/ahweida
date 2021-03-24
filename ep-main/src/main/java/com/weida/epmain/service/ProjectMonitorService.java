package com.weida.epmain.service;

import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.ProjectMonitor;
import com.weida.epmain.dto.vo.ProjectMonitorVO;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: zhaof
 * @Date: 2021/1/8 10:11
 * @Desc: TODO
 */
public interface ProjectMonitorService {

    List<ProjectMonitorVO> getProjectMonitors(Integer id);

    void editProjectMonitor(ProjectMonitor monitor);

    void addProjectMonitor(ProjectMonitor monitor);

    void delProjectMonitor(Integer id);
}
