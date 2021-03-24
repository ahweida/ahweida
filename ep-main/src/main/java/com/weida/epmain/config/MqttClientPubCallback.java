package com.weida.epmain.config;

import com.weida.epcommon.constants.MqttTopicConstant;
import com.weida.epmain.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 */
@Slf4j
@Service
public class MqttClientPubCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable arg0) {
        log.info("MqttClientPubCallback mqtt 失去了连接");
        MqttClient mqttPublishClient = (MqttClient) SpringUtil.getApplicationContext().getBean("mqttPublishClient");
        if (mqttPublishClient != null) {
            System.out.println("mqtt subscribeReconnect");
            try {
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                connOpts.setMaxInflight(100000);

                MqttConnectOptions options = new MqttConnectOptions();
                mqttPublishClient.setCallback(this);
                options.setConnectionTimeout(10);
                // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
                options.setKeepAliveInterval(20);
                //设置断开后重新连接
                options.setAutomaticReconnect(true);
                mqttPublishClient.connect(options);
            } catch (MqttException me) {
                me.printStackTrace();
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        System.out.println("mqtt 发送完成！");
    }


    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        String content = new String(message.getPayload(), "utf-8");
        log.info("收到mqtt消息,topic: " + topic + " ,content: " + content);
//        if (MqttClientUtil.executorService != null) {
//            MqttClientUtil.executorService.execute(new HandlerThread(topic, content));
//        }
    }


}