package com.weida.epmain.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProjectVO {
    private Integer id;

    private String projectName;

    private String nickName;

    private String longitude;

    private String latitude;

    private Integer customerId;

    private String customerName;

    private String serverIp;

    private String serverPort;

    private String serverUsername;

    private String serverPassword;

    private String mqttClientId;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private Integer status;

    private Integer offlineTime;

    private String remark;
}
