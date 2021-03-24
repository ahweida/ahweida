package com.weida.epmain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weida.epcommon.constants.MqttTopicConstant;
import com.weida.epmain.dto.*;
import com.weida.epmain.mapper.*;
import com.weida.epmain.service.LocalService;
import com.weida.epmain.service.MessageService;
import com.weida.epmain.service.ProjectNetService;
import com.weida.epmain.util.SnowflakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LocalServiceImpl implements LocalService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectControlMapper projectControlMapper;

    @Resource
    private ProjectMonitorMapper projectMonitorMapper;

    @Resource
    private ProjectNetMapper projectNetMapper;

    @Resource
    private DynamicControlMapper dynamicControlMapper;

    @Resource
    private DataGroupMapper dataGroupMapper;

    @Resource
    private DataDefineMapper dataDefineMapper;

    @Autowired
    private MessageService messageService;

    @Resource
    private MessageStatusMapper messageStatusMapper;

    @Autowired
    private SnowflakeUtil snowflakeUtil;

    @Autowired
    private ProjectNetService projectNetService;


    @Override
    public Object syncProject(Integer projectId) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        Project project = projectMapper.selectByPrimaryKey(projectId);
        Integer customerId = project.getCustomerId();
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        List<ProjectControl> projectControls = projectControlMapper.selectProjectControls(parameters);
        List<ProjectMonitor> projectMonitors = projectMonitorMapper.selectProjectMonitors(parameters);
        List<ProjectNet> projectNets = projectNetMapper.selectProjectNets(parameters);
        List<DynamicControl> dynamicControls = dynamicControlMapper.selectDynamicControls(parameters);
        List<DataGroup> dataGroups = dataGroupMapper.selectDataGroups(parameters);
        List<DataDefine> dataDefines = dataDefineMapper.selectDefines(parameters);

        JSONObject json = new JSONObject();
        json.put("customer", customer);
        json.put("project", project);
        json.put("projectControls", projectControls);
        json.put("projectMonitors", projectMonitors);
        json.put("projectNets", projectNets);
        json.put("dynamicControls", dynamicControls);
        json.put("dataGroups", dataGroups);
        json.put("dataDefines", dataDefines);
        json.put("sendTime", new Date().getTime());
        json.put("type", "syncProject");
        MessageStatus messageStatus = new MessageStatus();
        try {
            String  messageId = String.valueOf(snowflakeUtil.nextValue());
            json.put("messageId", messageId);
            messageStatus.setMessageId(messageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageStatus.setProjectId(projectId);
        String topic = MqttTopicConstant.TOPIC_PREFIX_CTL + "/" + project.getId() + "/" + MqttTopicConstant.TOPIC_SUFFIX_SYNC_PROJECT;
        messageStatus.setTopic(topic);
        messageStatus.setStatus(0);
        messageStatus.setCreateTime(new Date());
        messageStatusMapper.insert(messageStatus);
        messageService.send(topic, json.toJSONString());
        messageStatus.setStatus(1);
        messageStatusMapper.updateByMessageIdSelective(messageStatus);

        return json;
    }

    @Override
    public void syncControl(Integer projectId) {
        log.info("syncControl start ...projectId:{}",projectId);
        Project project = projectMapper.selectByPrimaryKey(projectId);

        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        List<ProjectControl> projectControls = projectControlMapper.selectProjectControls(parameters);
        JSONObject json = new JSONObject();
        json.put("projectControls", projectControls);
        json.put("sendTime", new Date().getTime());

        json.put("type", "syncControl");
        MessageStatus messageStatus = new MessageStatus();
        try {
            String  messageId = String.valueOf(snowflakeUtil.nextValue());
            json.put("messageId", messageId);
            messageStatus.setMessageId(messageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageStatus.setProjectId(projectId);
        String topic = MqttTopicConstant.TOPIC_PREFIX_CTL + "/" + project.getId() + "/" + MqttTopicConstant.TOPIC_SUFFIX_SYNC_CONTROL;
        messageStatus.setTopic(topic);
        messageStatus.setStatus(0);
        messageStatus.setCreateTime(new Date());
        messageStatusMapper.insert(messageStatus);
        messageService.send(topic, json.toJSONString());
        messageStatus.setStatus(1);
        messageStatusMapper.updateByPrimaryKey(messageStatus);
    }

    @Override
    public void syncAbpConfig(Integer projectId) {
        log.info("syncAbpConfig start ...projectId:{}",projectId);
        Project project = projectMapper.selectByPrimaryKey(projectId);

        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        List<DynamicControl> dynamicControls = dynamicControlMapper.selectDynamicControls(parameters);
        JSONObject json = new JSONObject();
        json.put("dynamicControls", dynamicControls);
        json.put("sendTime", new Date().getTime());

        json.put("type", "syncAbp");
        MessageStatus messageStatus = new MessageStatus();
        try {
            String  messageId = String.valueOf(snowflakeUtil.nextValue());
            json.put("messageId", messageId);
            messageStatus.setMessageId(messageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageStatus.setProjectId(projectId);
        String topic = MqttTopicConstant.TOPIC_PREFIX_CTL + "/" + project.getId() + "/" + MqttTopicConstant.TOPIC_SUFFIX_SYNC_ABP;
        messageStatus.setTopic(topic);
        messageStatus.setStatus(0);
        messageStatus.setCreateTime(new Date());
        messageStatusMapper.insert(messageStatus);
        messageService.send(topic, json.toJSONString());
        messageStatus.setStatus(1);
        messageStatusMapper.updateByPrimaryKey(messageStatus);
    }

    @Override
    public void cloudTrain(Integer projectId,String name) {
        log.info("cloudTrain start ...projectId:{},name:{}",projectId,name);
        Project project = projectMapper.selectByPrimaryKey(projectId);

        JSONObject json = new JSONObject();
        json.put("sendTime", new Date().getTime());
        json.put("netName", name);
        json.put("type", "train");
        MessageStatus messageStatus = new MessageStatus();
        try {
            String  messageId = String.valueOf(snowflakeUtil.nextValue());
            json.put("messageId", messageId);
            messageStatus.setMessageId(messageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageStatus.setProjectId(projectId);
        String topic = MqttTopicConstant.TOPIC_PREFIX_CTL + "/" + project.getId() + "/" + MqttTopicConstant.TOPIC_SUFFIX_TRAIN;
        messageStatus.setTopic(topic);
        messageStatus.setType("train");
        messageStatus.setStatus(0);
        messageStatus.setCreateTime(new Date());
        messageStatusMapper.insert(messageStatus);
        //发送消息
        messageService.send(topic, json.toJSONString());
        projectNetService.closeStatus(projectId,name);
        messageStatus.setStatus(1);
        messageStatusMapper.updateByPrimaryKey(messageStatus);
        log.info("cloudTrain end ...projectId:{}",projectId);
    }

}
