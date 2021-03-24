package com.weida.epmain.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.ProjectControl;
import com.weida.epmain.dto.ProjectMonitor;
import com.weida.epmain.dto.vo.ProjectMonitorVO;
import com.weida.epmain.service.ProjectMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/8 10:08
 * @Desc: 项目监控指标控制器
 */
@RestController
@RequestMapping("/monitor")
public class ProjectMonitorController {

    @Autowired
    private ProjectMonitorService projectMonitorService;

    @RequestMapping("/getProjectMonitors")
    public CommonResult getProjectMonitors(Integer projectId) {
        List<ProjectMonitorVO> data = projectMonitorService.getProjectMonitors(projectId);
        return CommonResult.success(data);
    }

    @PostMapping("/editProjectMonitor")
    public CommonResult editProjectMonitor(@Valid ProjectMonitor monitor, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        projectMonitorService.editProjectMonitor(monitor);
        return CommonResult.success("");
    }

    @PostMapping("/addProjectMonitor")
    public CommonResult addProjectMonitor(@Valid ProjectMonitor monitor, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        projectMonitorService.addProjectMonitor(monitor);
        return CommonResult.success("");
    }

    @PostMapping("/delProjectMonitor")
    public CommonResult delProjectMonitor(Integer id){
        projectMonitorService.delProjectMonitor(id);
        return CommonResult.success("");
    }


}
