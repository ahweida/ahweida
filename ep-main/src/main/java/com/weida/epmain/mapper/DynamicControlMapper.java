package com.weida.epmain.mapper;


import com.weida.epmain.dto.DynamicControl;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DynamicControlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DynamicControl record);

    int insertSelective(DynamicControl record);

    DynamicControl selectByPrimaryKey(Integer id);

    List<DynamicControl> selectByProjectId(@Param("projectId") Integer id);

    int updateByPrimaryKeySelective(DynamicControl record);

    int updateByPrimaryKey(DynamicControl record);

    List<DynamicControl> selectDynamicControls(Map<String, Object> parameters);
}