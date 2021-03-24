package com.weida.epmain.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.Chart;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.vo.ChartVO;
import com.weida.epmain.dto.vo.ExceptionLogVO;
import com.weida.epmain.service.ChartService;
import com.weida.epmain.service.ExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/exceptionLog")
@Slf4j
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @RequestMapping("/getExceptionLogPage")
    public CommonResult getExceptionLogPage(int pageNum, int pageSize, Integer projectId, String startTime, String endTime, String description)  {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("description", description);
        paramMap.put("projectId", projectId);
        PageInfo<ExceptionLogVO> data = exceptionLogService.getExceptionLogPage(paramMap);
        return CommonResult.success(data);
    }

}
