package com.weida.epuser.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.Role;
import com.weida.epuser.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RoleController")
@Slf4j
public class RoleController {
    @Autowired(required = false)
    private RoleService roleService;

    @PostMapping("/delRoleById")
    public CommonResult delRoleById(@RequestParam("id") Integer id) {
        try {
            Role role = new Role();
            role.setId(id);
            role.setDaleteFlag(1);

            roleService.delRoleById(role);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("删除角色成功");
        }
        return CommonResult.success("");
    }

}
