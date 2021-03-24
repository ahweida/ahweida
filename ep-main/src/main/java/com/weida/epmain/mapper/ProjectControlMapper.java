package com.weida.epmain.mapper;


import com.weida.epmain.dto.ProjectControl;

import java.util.List;
import java.util.Map;

public interface ProjectControlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectControl record);

    int insertSelective(ProjectControl record);

    ProjectControl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectControl record);

    int updateByPrimaryKey(ProjectControl record);

    List<ProjectControl> selectProjectControls(Map<String, Object> parameters);
}