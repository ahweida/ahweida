package com.weida.epmain.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.MessageStatusVO;
import com.weida.epmain.dto.vo.ProjectVO;
import com.weida.epmain.service.MessageStatusService;
import com.weida.epmain.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messageStatus")
@Slf4j
public class MessageStatusController {

    @Autowired
    private MessageStatusService messageStatusService;

    @RequestMapping("/getMessageStatusPage")
    public CommonResult getMessageStatusPage(int pageNum, int pageSize, Integer projectId, String startTime, String endTime, Integer status) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("projectId", projectId);
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("status", status);
        PageInfo<MessageStatusVO> data = messageStatusService.getMessageStatusPage(paramMap);
        return CommonResult.success(data);
    }


}
