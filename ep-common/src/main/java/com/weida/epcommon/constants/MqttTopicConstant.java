package com.weida.epcommon.constants;

/**
 * @description:
 * @author: liaoze
 * @date: 2020/12/31 下午3:58
 * @version:
 */
public class MqttTopicConstant {

    public static String TOPIC_PREFIX_MSG = "message";
    public static String TOPIC_PREFIX_CTL = "control";
    //云端主动发送的命令执行结果返回值
    public static String TOPIC_MESSAGE_RETURN = "messageReturn";

    //边缘端主动发送
    public static String TOPIC_MESSAGE_LOCAL_SEND = "LocalSend/trainModel";

    //消息控制split
    public static String SPLIT = "/";

    //边缘服务器发送数据给云端的消息回执
    public static String TOPIC_SUFFIX_RETURN_MESSAGE = "returnMessage";

    public static String TOPIC_SUFFIX_SYNC_PROJECT = "syncProject";

    public static String TOPIC_SUFFIX_SYNC_ABP = "syncAbpConfig";

    public static String TOPIC_SUFFIX_SYNC_CONTROL = "syncControl";

    public static String TOPIC_SUFFIX_TRAIN = "trainControl";


}
