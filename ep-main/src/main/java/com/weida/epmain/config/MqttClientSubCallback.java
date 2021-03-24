package com.weida.epmain.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weida.epcommon.constants.MqttTopicConstant;
import com.weida.epcommon.util.DateUtil;
import com.weida.epmain.dto.Data;
import com.weida.epmain.factory.MessageHandleFactory;
import com.weida.epmain.handle.MessageHandle;
import com.weida.epmain.mapper.DataMapper;
import com.weida.epmain.service.DataService;
import com.weida.epmain.service.MessageService;
import com.weida.epmain.service.WarnLogService;
import com.weida.epmain.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author
 */
@Service
@Slf4j
public class MqttClientSubCallback implements MqttCallback {

    @Autowired
    private MessageHandleFactory messageHandleFactory;


    @Override
    public void connectionLost(Throwable arg0) {
        MqttClient mqttSubClient = (MqttClient) SpringUtil.getApplicationContext().getBean("mqttSubClient");
        if (mqttSubClient != null) {
            log.info("mqtt subscribeReconnect");
            try {
                MqttConnectOptions options = new MqttConnectOptions();
                mqttSubClient.setCallback(this);
                options.setConnectionTimeout(10);
                // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
                options.setKeepAliveInterval(20);
                //设置断开后重新连接
                options.setAutomaticReconnect(true);
                mqttSubClient.connect(options);
                //订阅消息
                int[] Qos = {1,2,1};//0：最多一次 、1：最少一次 、2：只有一次
                String[] topic = {MqttTopicConstant.TOPIC_PREFIX_MSG,MqttTopicConstant.TOPIC_MESSAGE_RETURN,MqttTopicConstant.TOPIC_MESSAGE_LOCAL_SEND+"/#"};
                mqttSubClient.subscribe(topic, Qos);
            } catch (MqttSecurityException e) {
                e.printStackTrace();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
            @Override
            public void deliveryComplete (IMqttDeliveryToken arg0){
                log.info("mqtt 发送完成！");
            }

            @Override
            public void messageArrived (String topic, MqttMessage message) throws Exception {
                try {
                    String content = new String(message.getPayload(), "utf-8");
                    log.info("收到mqtt消息,topic: " + topic + " ,content: " + content);
                    MessageHandle messageHandle = messageHandleFactory.getMessageHandle(topic);
                    if (Objects.isNull(messageHandle)){
                        log.error("没有查到对应的topic处理器,topic:{}",topic);
                    }else{
                        messageHandle.handleMessageByTopic(content);
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                    e.printStackTrace();
                }


            }

        }