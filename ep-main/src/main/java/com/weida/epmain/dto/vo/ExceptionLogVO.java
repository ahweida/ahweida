package com.weida.epmain.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zhaof
 * @Date: 2021/2/20 15:02
 * @Desc: TODO
 */
@Data
public class ExceptionLogVO {
    private Integer id;

    private Integer projectId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String description;

    private String projectName;
}
