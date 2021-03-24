package com.weida.epmain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.weida.epcommon.util.DateUtil;
import com.weida.epmain.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @description:
 * @author: liaoze
 * @date: 2020/12/31 下午4:27
 * @version:
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {


    @Resource(name ="mqttPublishClient")
    private MqttClient mqttPublishClient;

    @Override
    public void send(String topic,String content) {
        log.info("send start ....topic:{},content:{}",topic,content);
        if (content.length() > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", content);

            byte[] array = null;
            try {
                array = jsonObject.toString().getBytes("utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            MqttMessage message = new MqttMessage(array);
            //发布消息
            try {
                log.info("message publish start ....time:{}", DateUtil.format(new Date(),DateUtil.DATE_FORMAT_SECOND));
                mqttPublishClient.publish(topic, message);
                log.info("message publish end ....time:{}", DateUtil.format(new Date(),DateUtil.DATE_FORMAT_SECOND));
            } catch (MqttException e) {
                log.info("消息发送失败,exception:{}",e);
                e.printStackTrace();
            }
        }
    }


}
