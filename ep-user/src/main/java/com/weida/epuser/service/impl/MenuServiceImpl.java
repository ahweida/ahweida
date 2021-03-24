package com.weida.epuser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.Menu;
import com.weida.epuser.mapper.MenuMapper;
import com.weida.epuser.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService{
    @Resource
    private  MenuMapper menuMapper;

    @Override
    public void addMenu(Menu menu){
        menuMapper.insert(menu);
    }

    @Override
    public void delMenuById(Menu menu) {
        menuMapper.delMenuById(menu);
    }

}
