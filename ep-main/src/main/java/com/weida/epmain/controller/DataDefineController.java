package com.weida.epmain.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.vo.DataDefineVO;
import com.weida.epmain.service.DataDefineService;
import com.weida.epcommon.util.CommonResult;
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
@RequestMapping("/define")
@Slf4j
public class DataDefineController {

    @Autowired
    private DataDefineService dataDefineService;

    @RequestMapping("/getDefinePage")
    public CommonResult getDefinePage(int pageNum, int pageSize, Integer projectId, String mysqlField, String tablestoreField, String dataName, Integer collectionGroupId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("projectId", projectId);
        paramMap.put("mysqlField", mysqlField);
        paramMap.put("tablestoreField", tablestoreField);
        paramMap.put("dataName", dataName);
        paramMap.put("collectionGroupId", collectionGroupId);
        PageInfo<DataDefineVO> data = dataDefineService.getDefinePage(paramMap);
        return CommonResult.success(data);
    }


    @PostMapping("/editDataDefine")
    public CommonResult editDataDefine(@Valid DataDefine dataDefine, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        dataDefineService.editDataDefine(dataDefine);
        return CommonResult.success("");
    }

    @PostMapping("/addDataDefine")
    public CommonResult addDataDefine(@Valid DataDefine dataDefine, BindingResult result) {
        if (result.hasErrors()) {
//            result.getAllErrors().forEach((error) -> {
//                FieldError fieldError = (FieldError) error;
//                // 属性
//                String field = fieldError.getField();
//                // 错误信息
//                String message = fieldError.getDefaultMessage();
//                System.out.println(field + ":" + message);
//
//            });
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        dataDefineService.addDataDefine(dataDefine);
        return CommonResult.success("添加成功");
    }

    @PostMapping("/delDataDefine")
    public CommonResult delDataDefine(Integer id) {
        dataDefineService.delDataDefine(id);
        return CommonResult.success("");
    }


    @RequestMapping("/getDataDefines")
    public CommonResult getDataDefines(Integer projectId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("projectId", projectId);
        List<DataDefine> data = dataDefineService.getDataDefines(paramMap);
        return CommonResult.success(data);
    }


}
