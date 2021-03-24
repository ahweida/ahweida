package com.weida.epmain.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.ProjectNet;
import com.weida.epmain.dto.vo.ProjectNetVO;
import com.weida.epmain.service.ProjectNetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/projectNet")
@Slf4j
public class ProjectNetController {

    @Autowired
    private ProjectNetService projectNetService;


    @RequestMapping("/getProjectNetPage")
    public CommonResult getProjectNetPage(int pageNum, int pageSize, Integer projectId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("projectId", projectId);
        PageInfo<ProjectNetVO> data = projectNetService.getProjectNetPage(paramMap);
        return CommonResult.success(data);
    }

    @PostMapping("/editProjectNet")
    public CommonResult editProjectNet(@Valid ProjectNet projectNet, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        projectNetService.editProjectNet(projectNet);
        return CommonResult.success("");
    }

    @PostMapping("/addProjectNet")
    public CommonResult addProjectNet(@Valid ProjectNet projectNet, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        projectNetService.addProjectNet(projectNet);
        return CommonResult.success("");
    }

    @PostMapping("/delProjectNet")
    public CommonResult delProjectNet(Integer id){
        projectNetService.delProjectNet(id);
        return CommonResult.success("");
    }


}
