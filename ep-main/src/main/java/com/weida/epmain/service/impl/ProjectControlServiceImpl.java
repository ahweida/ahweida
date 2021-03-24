package com.weida.epmain.service.impl;

import com.google.common.collect.Maps;
import com.weida.epmain.dto.ProjectControl;
import com.weida.epmain.mapper.ProjectControlMapper;
import com.weida.epmain.service.ProjectControlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProjectControlServiceImpl implements ProjectControlService {

    @Resource
    private ProjectControlMapper projectControlMapper;

    @Override
    public List<ProjectControl> getProjectControls(Integer projectId) {
        Map<String,Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        return projectControlMapper.selectProjectControls(parameters);
    }

    @Override
    public Integer addProjectControl(ProjectControl projectControl) {
        return projectControlMapper.insert(projectControl);
    }

    @Override
    public Integer deleteProjectControl(Integer id) {
        return projectControlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateProjectControl(ProjectControl projectControl) {
        return projectControlMapper.updateByPrimaryKey(projectControl);
    }
}
