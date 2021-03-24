package com.weida.epuser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.MenuRole;
import com.weida.epuser.mapper.MenuRoleMapper;
import com.weida.epuser.service.MenuRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

@Service
public class MenuRoleServiceImpl implements MenuRoleService{
    @Resource
    private MenuRoleMapper menuroleMapper;

    @Override
    public void  addMenuRole(MenuRole menurole){menuroleMapper.insert(menurole);}
}
