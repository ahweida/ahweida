package com.weida.epmain.controller;

import com.alibaba.fastjson.JSON;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.DynamicControl;
import com.weida.epmain.service.DynamicControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/2 上午10:37
 * @version:
 */
@Slf4j
@RestController
@RequestMapping("/adjust")
public class ProjectAdjustController {


    @Autowired
    private DynamicControlService dynamicControlService;

    @GetMapping("/getProjectAdjust")
    public CommonResult getProjectAdjust(Integer projectId){
        log.info("getProjectAdjust start ...projectId:{}",projectId);
        List<DynamicControl> dynamicControlInfoByProjectId = dynamicControlService.getDynamicControlInfoByProjectId(projectId);
        return  CommonResult.success(dynamicControlInfoByProjectId);
    }

    @PostMapping("/delProjectAdjust")
    public  CommonResult delProjectAdjust(Integer id){
        log.info("delProjectAdjust start ...id:{}",id);
        Integer flag = dynamicControlService.deleteProjectAdjustById(id);
        log.info("delProjectAdjust flag:{}",flag);
        if (flag == 1){
            return CommonResult.success("删除成功");
        }else{
            return CommonResult.success("删除失败");
        }
    }

    @PostMapping("editProjectAdjust")
    public  CommonResult editProjectAdjust(DynamicControl record){
        log.info("editProjectAdjust start ...record:{}", JSON.toJSONString(record));
        Integer flag = dynamicControlService.editProjectAdjust(record);
        if (flag == 1){
            return CommonResult.success("更新成功");
        }else{
            return CommonResult.success("更新失败");
        }
    }

    @PostMapping("addProjectAdjust")
    public  CommonResult addProjectAdjust(DynamicControl record){
        log.info("addProjectAdjust start ...record:{}", JSON.toJSONString(record));
        Integer flag = dynamicControlService.insertProjectAdjust(record);
        if (flag == 1){
            return CommonResult.success("添加成功");
        }else{
            return CommonResult.success("添加失败");
        }
    }
}
