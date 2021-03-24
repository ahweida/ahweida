package com.weida.epuser.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.CrmRelationUser;
import com.weida.epuser.service.CrmRelationUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/3/8 10:16
 * @Desc: TODO
 */
@RestController
@RequestMapping("/crmRelationUser")
@Slf4j
public class CrmRelationUserController {

    @Autowired
    private CrmRelationUserService crmRelationUserService;

    @RequestMapping("/getCrmRelationUserPage")
    public CommonResult getCrmRelationUserPage(int pageNum, int pageSize, String crmAccount, String account, Integer status) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("crmAccount", crmAccount);
        paramMap.put("account", account);
        paramMap.put("status", status);
        PageInfo<CrmRelationUser> data = crmRelationUserService.getCrmRelationUserPage(paramMap);
        return CommonResult.success(data);
    }

    @PostMapping("/editCrmRelationUser")
    public CommonResult editCrmRelationUser(@Valid CrmRelationUser crmRelationUser, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        crmRelationUserService.editCrmRelationUser(crmRelationUser);
        return CommonResult.success("");
    }

    @PostMapping("/addCrmRelationUser")
    public CommonResult addCrmRelationUser(@Valid CrmRelationUser crmRelationUser, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        crmRelationUserService.addCrmRelationUser(crmRelationUser);
        return CommonResult.success("");
    }

    @PostMapping("/delCrmRelationUser")
    public CommonResult delCrmRelationUser(Integer id){
        crmRelationUserService.delCrmRelationUser(id);
        return CommonResult.success("");
    }


}
