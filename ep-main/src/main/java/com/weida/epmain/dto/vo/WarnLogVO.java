package com.weida.epmain.dto.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: zhaof
 * @Date: 2021/1/22 9:41
 * @Desc: TODO
 */
@Data
public class WarnLogVO {

    private Integer id;
    private Integer projectId;
    private Integer dataDefineId;
    private String projectName;
    private String dataName;
    private Integer warnType;
    private Date collectTime;
    private Double dataValue;
    private Double normalMin;
    private Double normalMax;

}
