package com.weida.epuser.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.Dict;
import com.weida.epuser.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictController")
@Slf4j
public class DictController {
    @Autowired
    private DictService dictService;

    @PostMapping("delDictById")
    public CommonResult delDictById(@RequestParam("id") Integer id){
        try {
            Dict dict = new Dict();
            dict.setId(id);

            dictService.delDictById(dict);
        } catch (Exception e){
            log.error(e.getMessage());
            return CommonResult.fail("删除字典失败");
        }
        return CommonResult.success("");
    }

}
