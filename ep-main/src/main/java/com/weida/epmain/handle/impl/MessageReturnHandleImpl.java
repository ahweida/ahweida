package com.weida.epmain.handle.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weida.epmain.dto.Data;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.MessageStatus;
import com.weida.epmain.factory.MessageHandleFactory;
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
public class MessageReturnHandleImpl implements MessageHandle {

    @Autowired
    private MessageStatusMapper messageStatusMapper;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Autowired
    private ProjectNetService projectNetService;

    @Override
    public void handleMessageByTopic(String content) {
        log.info("MessageReturnHandleImpl start ... content:{}",content);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONObject msg = jsonObject.getJSONObject("msg");
        MessageStatus messageStatus = JSON.toJavaObject(msg, MessageStatus.class);
        messageStatusMapper.updateByMessageIdSelective(messageStatus);
        if (messageStatus.getStatus()!=3){
            ExceptionLog exceptionLog = new ExceptionLog();
            exceptionLog.setCreateTime(new Date());
            exceptionLog.setDescription(messageStatus.getType()+"执行失败");
            exceptionLog.setProjectId(messageStatus.getProjectId());
            exceptionLogService.addExceptionLog(exceptionLog);
        }
        //训练状态放开
        if ("train".equals(messageStatus.getType())){
            String netName  = msg.getString("netName");
            projectNetService.openStatus(messageStatus.getProjectId(),netName);
        }
        log.info("MessageReturnHandleImpl end ... ");
    }
}
