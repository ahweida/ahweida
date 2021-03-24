package com.weida.epuser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.Role;
import com.weida.epuser.mapper.RoleMapper;
import com.weida.epuser.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private  RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addRole(Role role){roleMapper.insert(role);}

    @Override
    public void delRoleById(Role role) {
        roleMapper.delRoleById(role);
    }
}
