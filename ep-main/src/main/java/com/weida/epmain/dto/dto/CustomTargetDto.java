package com.weida.epmain.dto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zhaof
 * @Date: 2021/2/2 16:45
 * @Desc: TODO
 */
@Data
public class CustomTargetDto {

    private  Integer projectId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Integer defineIdOne;
    private Integer defineIdTwo;
    private Integer defineIdThree;
    private Boolean oneFlag;
    private Boolean oneAvgFlag;
    private Boolean twoFlag;
    private Boolean twoAvgFlag;
    private Boolean threeFlag;
    private Boolean threeAvgFlag;

    private Double oneValue;
    private Double twoValue;
    private Double threeValue;
    private Double oneAvgValue;
    private Double twoAvgValue;
    private Double threeAvgValue;

    private String collectTime;

}
