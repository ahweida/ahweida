package com.weida.epmain.service;

import com.weida.epmain.dto.ProjectControl;

import java.util.List;
import java.util.Map;

public interface ProjectControlService {

    List<ProjectControl> getProjectControls(Integer id);

    Integer addProjectControl(ProjectControl projectControl);

    Integer deleteProjectControl(Integer id);

    Integer updateProjectControl(ProjectControl projectControl);

}
