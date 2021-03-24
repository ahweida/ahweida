package com.weida.epmain.dto.vo;

import lombok.Data;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/8 10:31
 * @Desc: TODO
 */
@Data
public class ProjectMonitorVO {
    private Integer id;

    private Integer projectId;

    private String projectName;

    private Integer dataDefineId;

    private String dataName;

    private Integer level;

    private Double normalMin;

    private Double normalMax;

    private Double warnMin;

    private Double warnMax;

    private Double beyondMin;

    private Double beyondMax;

    private Integer xcoord;

    private Integer ycoord;
}
