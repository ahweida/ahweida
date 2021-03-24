package com.weida.epuser.controller;

import com.weida.epuser.service.MenuRoleService;
import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.MenuRole;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menuroleController")
@Slf4j

public class MenuRoleController {
    @Autowired
    private MenuRoleService menuroleService;

    /**
     * @Description: TODO 添加菜单角色
     * @Author: YY
     * @CreateDate: 2021/3/24/10：25
     * @param: [menurole]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/

    @PostMapping("/menuroleMenu")
    public CommonResult addMenu(@Valid MenuRole menurole,BindingResult result){
        if (result.hasErrors()){
            return  CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try{
            menuroleService.addMenuRole(menurole);
            return CommonResult.success("添加菜单角色成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return  CommonResult.fail("添加菜单角色失败");
        }
    }
}
