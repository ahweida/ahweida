package com.weida.epmain.controller;

import com.weida.epcommon.jwt.UserInfo;
import com.weida.epcommon.util.CommonResult;
import com.weida.epcommon.jwt.JwtUtils;
import com.weida.epcommon.jwt.Payload;
import com.weida.epmain.service.LocalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @Auther: zhaofei
 * @Date: 2021/1/11 14:46
 * @Desc: 与边缘端交互相关的操作
 */
@RestController
@RequestMapping("/local")
@Slf4j
@EnableScheduling
public class LocalController {

    @Autowired
    private LocalService localService;

    @PostMapping("/syncProject")
    public CommonResult syncProject(Integer projectId)  {
        Object syncData = localService.syncProject(projectId);
        return CommonResult.success(syncData);
    }

    @PostMapping("/syncControl")
    public CommonResult syncControl(Integer projectId)  {
        localService.syncControl(projectId);
        return CommonResult.success("同步命令发送成功");
    }

    @PostMapping("/syncAbpConfig")
    public CommonResult syncAbpConfig(Integer projectId)  {
        localService.syncAbpConfig(projectId);
        return CommonResult.success("同步命令发送成功");
    }

    @PostMapping("/cloudTrain")
    public CommonResult cloudTrain(Integer projectId,String name)  {
        localService.cloudTrain(projectId, name);
        return CommonResult.success("训练命令发送成功");
    }



//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void syncProjec()  {
//        log.info("定时任务执行,开始同步边缘端数据-------------");
//        Integer projectId = 2020002;
//       localService.syncProject(projectId);
//    }


}
