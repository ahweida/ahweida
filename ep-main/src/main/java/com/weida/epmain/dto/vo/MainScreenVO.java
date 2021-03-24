package com.weida.epmain.dto.vo;

import lombok.Data;

@Data
public class MainScreenVO {
    private Integer id;

    private String mainName;

    private Integer monitorId;

    private Integer projectId;

    private String projectName;

    private Integer offlineTime;

    private Integer parentId;

}
