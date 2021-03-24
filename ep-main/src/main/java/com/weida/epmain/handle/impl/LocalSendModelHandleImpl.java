package com.weida.epmain.handle.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.MessageStatus;
import com.weida.epmain.dto.dto.NeuralNet;
import com.weida.epmain.handle.MessageHandle;
import com.weida.epmain.mapper.MessageStatusMapper;
import com.weida.epmain.service.ExceptionLogService;
import com.weida.epmain.service.ProjectNetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/4 上午10:40
 * @version:
 */
@Slf4j
@Service
public class LocalSendModelHandleImpl implements MessageHandle {

    @Autowired
    private MessageStatusMapper messageStatusMapper;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Autowired
    private ProjectNetService projectNetService;

    @Override
    public void handleMessageByTopic(String content) {
        log.info("LocalSendModel start ... content:{}",content);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONObject msg = jsonObject.getJSONObject("msg");
        Integer  projectId = jsonObject.getInteger("projectId");
        NeuralNet neuralNet = JSON.toJavaObject(msg, NeuralNet.class);
        projectNetService.updateVersion(projectId,neuralNet.getName(),neuralNet.getVersion());
        log.info("LocalSendModel end ... ");
    }
}
