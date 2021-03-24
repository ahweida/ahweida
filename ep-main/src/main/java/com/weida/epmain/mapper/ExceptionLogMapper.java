package com.weida.epmain.mapper;

import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.vo.ExceptionLogVO;

import java.util.List;
import java.util.Map;

public interface ExceptionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionLog record);

    int insertSelective(ExceptionLog record);

    ExceptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionLog record);

    int updateByPrimaryKey(ExceptionLog record);

    List<ExceptionLogVO> selectExceptionLog(Map<String, Object> parameters);
}