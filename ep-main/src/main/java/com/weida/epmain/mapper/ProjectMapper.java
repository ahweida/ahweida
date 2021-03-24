package com.weida.epmain.mapper;


import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.ProjectVO;

import java.util.List;
import java.util.Map;

public interface ProjectMapper {

    List<Project> selectProjects(Map<String, Object> parameters);

    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<ProjectVO> selectList(Map<String, Object> parameters);
}