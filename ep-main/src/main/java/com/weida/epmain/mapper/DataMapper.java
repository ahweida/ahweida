package com.weida.epmain.mapper;


import com.weida.epmain.dto.Data;
import com.weida.epmain.dto.dto.CustomTargetDto;
import com.weida.epmain.dto.vo.DataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    Data selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);

    List<DataVO> selectByDynamicSql(@Param("dynamicSql") String dynamicSql);

    Map<String, Object> selectCenterDatas(Map<String, Object> parameters);

    List<Map<String, Object>> selectCustomTarget(Map<String, Object> parameters);

    List<Map<String, Object>> selectCustomTargetAvg(Map<String, Object> parameters);

    List<Map<String, Object>> selectLastCollectTime();
}