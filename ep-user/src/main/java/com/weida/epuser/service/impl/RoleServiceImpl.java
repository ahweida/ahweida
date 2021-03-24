package com.weida.epuser.service.impl;

import com.weida.epuser.dto.Role;
import com.weida.epuser.mapper.RoleMapper;
import com.weida.epuser.service.RoleService;

import javax.annotation.Resource;

public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void delRoleById(Role role) {
        roleMapper.deleteByPrimaryKey(role.getId());
    }
}
