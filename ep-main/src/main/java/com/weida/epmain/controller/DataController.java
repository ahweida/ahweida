package com.weida.epmain.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.Data;
import com.weida.epmain.service.DataService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {




    @Autowired
    private DataService dataService;

    @PostMapping("/illegal/addData")
    public CommonResult addData(Data data){
        log.info("进入/illegal/addData方法");
        dataService.addData(data);
        return CommonResult.success("");
    }

}
