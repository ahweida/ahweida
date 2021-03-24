package com.weida.epuser.service;

import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.Menu;
import java.util.List;
import java.util.Map;


public interface MenuService {
    void addMenu(Menu menu);
    void delMenuById(Menu menu);
}

