package com.weida.epmain.mapper;

import com.weida.epmain.dto.WarnLog;

public interface WarnLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarnLog record);

    int insertSelective(WarnLog record);

    WarnLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarnLog record);

    int updateByPrimaryKey(WarnLog record);
}