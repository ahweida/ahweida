package com.weida.epmain.controller;

import com.alibaba.fastjson.JSON;
import com.weida.epmain.dto.ProjectControl;
import com.weida.epmain.service.ProjectControlService;
import com.weida.epcommon.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/control")
@Slf4j
public class ProjectControlController {

    @Autowired
    private ProjectControlService projectControlService;

    @RequestMapping("/getProjectControls")
    public CommonResult getProjectControls(Integer projectId) {
        log.info("getProjectControls start ...projectId:{}", projectId);
        List<ProjectControl> data = projectControlService.getProjectControls(projectId);
        return CommonResult.success(data);
    }


    @PostMapping("/addProjectControl")
    public CommonResult addProjectControl(ProjectControl projectControl) {
        log.info("addProjectControl start ...projectControl:{}", JSON.toJSONString(projectControl));
        Integer flag = projectControlService.addProjectControl(projectControl);
        log.info("addProjectControl flag:{}",flag);
        if (flag == 1){
            return CommonResult.success("新增成功");
        }else{
            return CommonResult.success("新增失败");
        }
    }


    @PostMapping("/editProjectControl")
    public CommonResult editProjectControl(ProjectControl projectControl) {
        log.info("editProjectControl start ...projectControl:{}", JSON.toJSONString(projectControl));
        Integer flag = projectControlService.updateProjectControl(projectControl);
        log.info("editProjectControl flag:{}",flag);
        if (flag == 1){
            return CommonResult.success("更新成功");
        }else{
            return CommonResult.success("更新失败");
        }
    }

    @PostMapping("/delProjectControl")
    public CommonResult delProjectControl(Integer id) {
        log.info("delProjectControl start ...id:{}",id);
        Integer flag = projectControlService.deleteProjectControl(id);
        log.info("delProjectControl flag:{}",flag);
        if (flag == 1){
            return CommonResult.success("删除成功");
        }else{
            return CommonResult.success("删除失败");
        }
    }



}
