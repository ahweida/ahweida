package com.weida.epmain.mapper;

import com.weida.epmain.dto.ProjectNet;
import com.weida.epmain.dto.vo.ProjectNetVO;

import java.util.List;
import java.util.Map;

public interface ProjectNetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectNet record);

    int insertSelective(ProjectNet record);

    ProjectNet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectNet record);

    int updateByPrimaryKey(ProjectNet record);

    List<ProjectNet> selectProjectNets(Map<String, Object> parameters);

    List<ProjectNetVO> selectList(Map<String, Object> parameters);

    int updateStatus(ProjectNet record);

    int updateVersion(ProjectNet record);

}