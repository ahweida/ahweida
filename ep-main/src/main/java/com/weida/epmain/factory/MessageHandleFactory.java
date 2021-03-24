package com.weida.epmain.factory;

import com.google.common.collect.Maps;
import com.weida.epcommon.constants.MqttTopicConstant;
import com.weida.epmain.handle.MessageHandle;
import com.weida.epmain.handle.impl.DataMessageHandleImpl;
import com.weida.epmain.handle.impl.LocalSendModelHandleImpl;
import com.weida.epmain.handle.impl.MessageReturnHandleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/4 上午10:33
 * @version:
 */
@Service
public class MessageHandleFactory {


    @Autowired
    private MessageReturnHandleImpl messageReturnHandle;


    @Autowired
    private DataMessageHandleImpl dataMessageHandle;

    @Autowired
    private LocalSendModelHandleImpl localSendModelHandle;


    public MessageHandle getMessageHandle(String topic){
        Map<String, MessageHandle> map = Maps.newHashMap();
        //(topic，handle)
        map.put(MqttTopicConstant.TOPIC_PREFIX_MSG,dataMessageHandle);
        map.put(MqttTopicConstant.TOPIC_MESSAGE_RETURN,messageReturnHandle);
        map.put(MqttTopicConstant.TOPIC_MESSAGE_LOCAL_SEND,localSendModelHandle);
        return map.get(topic);
    }





}
