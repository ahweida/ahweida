package com.weida.epmain.handle;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/4 上午10:34
 * @version:
 */

public interface MessageHandle {

    /*
     * @Author: liaoze
     * @Description: 消息处理接口
     * @Date: 2021/2/22
     * @param content
     * @Return: void
     **/
    void handleMessageByTopic(String content);
}
