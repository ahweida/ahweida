package com.weida.epmain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Project {
    @NotNull(message = "项目编号不能为空")
    private Integer id;

    @NotBlank(message = "项目名称不能为空")
    private String projectName;

    @NotBlank(message = "项目简称不能为空")
    private String nickName;

    @NotBlank(message = "经度不能为空")
    private String longitude;

    @NotBlank(message = "纬度不能为空")
    private String latitude;

    @NotNull(message = "所属客户不能为空")
    private Integer customerId;

    @NotBlank(message = "服务IP不能为空")
    private String serverIp;

    @NotBlank(message = "服务端口不能为空")
    private String serverPort;

    private String serverUsername;

    private String serverPassword;

    private String mqttClientId;

    @NotBlank(message = "开始时间不能为空")
    private String startTime;

    @NotBlank(message = "结束时间不能为空")
    private String endTime;

    @NotNull(message = "状态不能为空")
    private Integer status;

    private Integer offlineStatus;

    @NotNull(message = "离线时长不能为空")
    private Integer offlineTime;

    private String imageUrl;

    private String remark;

    private String createTime;

    private String updateTime;

}