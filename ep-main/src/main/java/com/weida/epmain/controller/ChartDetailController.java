package com.weida.epmain.controller;


import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.ChartDetail;
import com.weida.epmain.service.ChartDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chartDetail")
@Slf4j
public class ChartDetailController {

    @Autowired
    private ChartDetailService chartDetailService;

    @RequestMapping("/getChartDetails")
    public CommonResult getChartDetails(Integer chartMainId)  {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("chartMainId", chartMainId);
        List<ChartDetail> data = chartDetailService.getChartDetails(paramMap);
        return CommonResult.success(data);
    }


    @PostMapping("/addChartDetail")
    public CommonResult addChartDetail(@Valid ChartDetail chartDetail, BindingResult result)  {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        if(chartDetail.getDataType() == 3 && chartDetail.getConstantValue() == null){
            return CommonResult.fail("常量值不能为空");
        }
        if(chartDetail.getDataType() != 3 && chartDetail.getDataDefineId() == null){
            return CommonResult.fail("监控指标字段不能为空");
        }
        chartDetailService.addChartDetail(chartDetail);
        return CommonResult.success("");
    }

    @PostMapping("/editChartDetail")
    public CommonResult editChartDetail(@Valid ChartDetail chartDetail, BindingResult result)  {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        if(chartDetail.getDataType() == 3 && chartDetail.getConstantValue() == null){
            return CommonResult.fail("常量值不能为空");
        }
        if(chartDetail.getDataType() != 3 && chartDetail.getDataDefineId() == null){
            return CommonResult.fail("监控指标字段不能为空");
        }
        chartDetailService.editChartDetail(chartDetail);
        return CommonResult.success("");
    }

    @PostMapping("/delChartDetail")
    public CommonResult delChartDetail(Integer id)  {
        chartDetailService.delChartDetail(id);
        return CommonResult.success("");
    }

}
