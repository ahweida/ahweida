package com.weida.epmain.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.ProjectVO;
import com.weida.epmain.service.ProjectService;
import com.weida.epcommon.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/getProjectPage")
    public CommonResult getProjectPage(int pageNum, int pageSize, String projectName, String customerId, Integer status) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("projectName", projectName);
        paramMap.put("customerId", customerId);
        paramMap.put("status", status);
        PageInfo<ProjectVO> data = projectService.getProjectPage(paramMap);
        return CommonResult.success(data);
    }

    @PostMapping("/editProject")
    public CommonResult editProject(@Valid Project project, BindingResult result) throws ParseException {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        projectService.editProject(project);
        return CommonResult.success("");
    }

    @PostMapping("/addProject")
    public CommonResult addProject(@Valid Project project, BindingResult result) throws ParseException {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        //默认新建的项目是离线状态 0-离线 1-在线
        project.setOfflineStatus(0);
        projectService.addProject(project);
        return CommonResult.success("");
    }

    @PostMapping("/delProject")
    public CommonResult delCustomer(Integer id){
        projectService.delProject(id);
        return CommonResult.success("");
    }

    @PostMapping("/endProject")
    public CommonResult endProject(Integer id) throws ParseException {
        projectService.endProject(id);
        return CommonResult.success("");
    }

    @RequestMapping("/getProjects")
    public CommonResult getProjects(Integer status) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("status", status);
        List<Project> data = projectService.getProjects(paramMap);
        return CommonResult.success(data);
    }

}
