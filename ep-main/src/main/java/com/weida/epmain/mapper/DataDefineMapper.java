package com.weida.epmain.mapper;

import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.vo.DataDefineVO;

import java.util.List;
import java.util.Map;

public interface DataDefineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDefine record);

    int insertSelective(DataDefine record);

    DataDefine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDefine record);

    int updateByPrimaryKey(DataDefine record);

    List<DataDefineVO> selectDataDefines(Map<String, Object> parameters);

    List<DataDefine> selectDefines(Map<String, Object> parameters);

    List<DataDefine> selectDefinesWithMysqlNotNull(Map<String, Object> parameters);

}