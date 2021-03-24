package com.weida.epuser.service;

import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.Role;
import org.springframework.data.relational.core.sql.In;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Role selectByPrimaryKey(Integer id);

    void addRole(Role role);

    void delRoleById(Role role);
}
