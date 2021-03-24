package com.weida.epuser.controller;

import com.weida.epuser.service.RoleService;
import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.Role;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/roleController")
@Slf4j
public class RoleController {
    @Autowired
    private  RoleService roleService;


    /**
     * @Description: TODO 添加角色
     * @Author: YY
     * @CreateDate: 2021/3/23/21:58
     * @param: [role]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/addRole")
    public CommonResult addRole(@Valid Role role,BindingResult result){
        if (result.hasErrors()){
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            roleService.addRole(role);
            return CommonResult.success("添加角色成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("添加角色失败");
        }
    }

    @PostMapping("/selectByPrimaryKey")
    public CommonResult selectByPrimaryKey(@RequestParam("id") Integer id){
        Role role = new Role();
        try {
            role = roleService.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("获取角色失败");
        }
        return CommonResult.success("");

    }


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
