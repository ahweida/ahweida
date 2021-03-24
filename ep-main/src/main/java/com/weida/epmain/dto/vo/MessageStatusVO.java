package com.weida.epmain.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zhaof
 * @Date: 2021/2/22 17:03
 * @Desc: TODO
 */
@Data
public class MessageStatusVO {
    private Integer id;

    private String messageId;

    private String type;

    private String topic;

    private Integer projectId;

    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String projectName;
}
