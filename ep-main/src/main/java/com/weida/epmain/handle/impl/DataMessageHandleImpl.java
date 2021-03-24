package com.weida.epmain.handle.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weida.epcommon.constants.MqttTopicConstant;
import com.weida.epcommon.util.DateUtil;
import com.weida.epmain.dto.Data;
import com.weida.epmain.handle.MessageHandle;
import com.weida.epmain.service.DataService;
import com.weida.epmain.service.MessageService;
import com.weida.epmain.service.WarnLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/4 上午10:41
 * @version:
 */
@Slf4j
@Service
public class DataMessageHandleImpl implements MessageHandle {

    @Autowired
    private DataService dataService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private WarnLogService warnLogService;


    @Override
    public void handleMessageByTopic(String content) {
        log.info("DataMessageHandleImpl start ... content:{}",content);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONObject msg = jsonObject.getJSONObject("data");
        Data data = JSON.toJavaObject(msg, Data.class);

        String colletTime = DateUtil.timeStamp2Date(data.getCollectTime(), "yyyy-MM-dd HH:mm:ss");
        data.setCollectTime(colletTime);
        dataService.addData(data);

        JSONObject json = new JSONObject();
        json.put("batchId", data.getBatchId());
        String rtopic = MqttTopicConstant.TOPIC_PREFIX_CTL + "/" + data.getProjectId() + "/returnMessage";
        messageService.send(rtopic, json.toJSONString());

        warnLogService.saveWarnLog(msg);
        log.info("DataMessageHandleImpl end ... ");
    }
}
