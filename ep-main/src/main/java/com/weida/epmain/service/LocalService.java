package com.weida.epmain.service;

public interface LocalService {

    /*
     * @Description: 项目属性同步
     * @Date: 2021/2/3
     * @param projectId
     * @Return: java.lang.Object
     **/
    Object syncProject(Integer projectId);

    /*
     * @Author: liaoze
     * @Description: 边缘服务 - 控制变量同步
     * @Date: 2021/2/3
     * @param projectId
     * @Return:
     **/
    void syncControl(Integer projectId);

    /*
     * @Author: liaoze
     * @Description: abp配置表同步
     * @Date: 2021/2/3
     * @param projectId
     * @Return: 
     **/
    void syncAbpConfig(Integer projectId);

    /*
     * @Author: liaoze
     * @Description: 云端下发训练命令
     * @Date: 2021/2/3
     * @param projectId
     * @Return:
     **/
    void cloudTrain(Integer projectId,String name);
}
