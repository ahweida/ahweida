package com.weida.epmain.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epmain.service.ChartService;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.Chart;
import com.weida.epmain.dto.vo.ChartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {

    @Autowired
    private ChartService chartService;

    @RequestMapping("/getChartPage")
    public CommonResult getChartPage(int pageNum, int pageSize, String projectId)  {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("projectId", projectId);
        PageInfo<ChartVO> data = chartService.getChartPage(paramMap);
        return CommonResult.success(data);
    }


    @PostMapping("/addChart")
    public CommonResult addChart(@Valid Chart chart, BindingResult result)  {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        chartService.addChart(chart);
        return CommonResult.success("");
    }

    @PostMapping("/editChart")
    public CommonResult editChart(@Valid Chart chart, BindingResult result)  {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        chartService.editChart(chart);
        return CommonResult.success("");
    }

    @PostMapping("/delChart")
    public CommonResult delChart(Integer id)  {
        chartService.delChart(id);
        return CommonResult.success("");
    }

}
