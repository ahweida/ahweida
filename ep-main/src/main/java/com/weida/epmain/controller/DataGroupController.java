package com.weida.epmain.controller;

import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.DataGroup;
import com.weida.epmain.dto.ProjectControl;
import com.weida.epmain.service.DataGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class DataGroupController {

    @Autowired
    private DataGroupService dataGroupService;

    @RequestMapping("/getDataGroups")
    public CommonResult getDataGroups(Integer projectId) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        List<DataGroup> data = dataGroupService.getDataGroups(parameters);
        return CommonResult.success(data);
    }



}
