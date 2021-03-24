package com.weida.epmain.service;

/**
 * @description:
 * @author: liaoze
 * @date: 2020/12/31 下午4:27
 * @version:
 */
public interface MessageService {

    void send(String topic,String content);
}
