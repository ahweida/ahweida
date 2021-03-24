package com.weida.epuser.mapper;

import com.weida.epuser.dto.DictItem;

public interface DictItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictItem record);

    int insertSelective(DictItem record);

    DictItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictItem record);

    int updateByPrimaryKey(DictItem record);

    void delDictItemById(DictItem dictItem);
}