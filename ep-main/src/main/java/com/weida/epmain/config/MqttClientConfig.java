package com.weida.epmain.config;

import com.weida.epcommon.constants.MqttTopicConstant;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zx
 */
@Configuration
public class MqttClientConfig {

	/**
	 * mqtt broker 连接配置,填自己的mqtt地址,及账号密码
	 */
	//测试环境
	private static String broker = "tcp://47.105.173.228:1883";
	private static String username = "";
	private static String password = "";

	@Autowired
	private MqttClientSubCallback mqttClientSubCallback;

	@Autowired
	private MqttClientPubCallback mqttClientPubCallback;


	@Bean("mqttSubClient")
	public MqttClient createSubClient() {
		String clientId = "mqttServer"+System.currentTimeMillis();
		MqttClient mqttClient = null;
		try {
			//subscribe client
			mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			mqttClient.setCallback(mqttClientSubCallback);
			options.setConnectionTimeout(10);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);
			//设置断开后重新连接
			options.setAutomaticReconnect(true);
			mqttClient.connect(options);
			//订阅消息
			int[] Qos = {1,2,1};//0：最多一次 、1：最少一次 、2：只有一次
			String[] topic = {MqttTopicConstant.TOPIC_PREFIX_MSG,MqttTopicConstant.TOPIC_MESSAGE_RETURN,MqttTopicConstant.TOPIC_MESSAGE_LOCAL_SEND+"/#"};
			mqttClient.subscribe(topic, Qos);
		} catch (MqttException me) {
			me.printStackTrace();
		}
		return mqttClient;
	}


	@Bean("mqttPublishClient")
	public MqttClient createPubClient() {
		String clientId = "mqttServer"+System.currentTimeMillis();
		MqttClient mqttPublishClient = null;
		try {
			//publish client
			mqttPublishClient = new MqttClient(broker, clientId + "-publish", new MemoryPersistence());
			mqttPublishClient.setCallback(mqttClientPubCallback);

			MqttConnectOptions options = new MqttConnectOptions();
			options.setConnectionTimeout(10);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);
			//设置断开后重新连接
			options.setAutomaticReconnect(true);
			mqttPublishClient.connect(options);

		} catch (MqttException me) {
			me.printStackTrace();
		}
		return mqttPublishClient;
	}

}
