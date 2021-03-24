package com.weida.epuser.controller;

import com.weida.epuser.service.MenuService;
import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.Menu;
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
@RequestMapping("/menuController")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;


    /**
     * @Description: TODO 添加菜单
     * @Author: YY
     * @CreateDate: 2021/3/23/20:57
     * @param: [menu]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/

    @PostMapping("/addMenu")
    public CommonResult addMenu(@Valid Menu menu, BindingResult result){
        if (result.hasErrors()){
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try{
            menuService.addMenu(menu);
            return CommonResult.success("添加菜单成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("添加菜单失败");
        }
    }

    @PostMapping("/delMenuById")
    public CommonResult delMenuById(@RequestParam("id") Integer id){
        try {
            Menu menu = new Menu();
            menu.setId(id);
            menu.setParentId(-1);
            menu.setMenuType(-1);

            menuService.delMenuById(menu);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("删除菜单失败");
        }
        return CommonResult.success("");
    }

}
