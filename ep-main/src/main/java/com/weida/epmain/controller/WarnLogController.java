package com.weida.epmain.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.Project;
import com.weida.epmain.service.WarnLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/1/22 15:03
 * @Desc: TODO
 */
@RestController
@RequestMapping("/warnLog")
public class WarnLogController {

    @Autowired
    private WarnLogService warnLogService;

    @RequestMapping("/saveWarnLog")
    public CommonResult saveWarnLog(@RequestParam Map<String, Object> map) {
        String string = JSONUtils.toJSONString(map);
        warnLogService.saveWarnLog(JSONObject.parseObject(string));
        return CommonResult.success("");
    }
}
