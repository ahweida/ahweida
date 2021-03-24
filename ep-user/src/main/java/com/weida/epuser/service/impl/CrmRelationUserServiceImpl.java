package com.weida.epuser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.CrmRelationUser;
import com.weida.epuser.mapper.CrmRelationUserMapper;
import com.weida.epuser.service.CrmRelationUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/3/8 10:08
 * @Desc: TODO
 */
@Service
public class CrmRelationUserServiceImpl implements CrmRelationUserService {

    @Resource
    private CrmRelationUserMapper crmRelationUserMapper;

    @Override
    public PageInfo<CrmRelationUser> getCrmRelationUserPage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<CrmRelationUser> crmRelationUserMappers = crmRelationUserMapper.selectList(paramMap);
        return new PageInfo<>(crmRelationUserMappers);
    }

    @Override
    public void editCrmRelationUser(CrmRelationUser crmRelationUser) {
        crmRelationUserMapper.updateByPrimaryKey(crmRelationUser);
    }

    @Override
    public void addCrmRelationUser(CrmRelationUser crmRelationUser) {
        crmRelationUserMapper.insert(crmRelationUser);
    }

    @Override
    public void delCrmRelationUser(Integer id) {
        crmRelationUserMapper.deleteByPrimaryKey(id);
    }

}
