package com.weida.epuser.mapper;

import com.weida.epuser.dto.CrmRelationUser;

import java.util.List;
import java.util.Map;

public interface CrmRelationUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmRelationUser record);

    int insertSelective(CrmRelationUser record);

    CrmRelationUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrmRelationUser record);

    int updateByPrimaryKey(CrmRelationUser record);

    List<CrmRelationUser> selectList(Map<String, Object> paramMap);

    CrmRelationUser selectByCrmAccount(String crmAccount);

}