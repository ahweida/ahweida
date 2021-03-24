package com.weida.epmain.mapper;


import com.weida.epmain.dto.DataGroup;

import java.util.List;
import java.util.Map;

public interface DataGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataGroup record);

    int insertSelective(DataGroup record);

    DataGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataGroup record);

    int updateByPrimaryKey(DataGroup record);

    List<DataGroup> selectDataGroups(Map<String, Object> parameters);
}