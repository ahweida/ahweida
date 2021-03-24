package com.weida.epmain.dto.vo;

import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.ProjectMonitor;
import com.weida.epmain.dto.WarnLog;
import lombok.Data;

/**
 * @Description: TODO
 * @Author: HX
 * @CreateDate: 2021/1/4/16:45
 * @param:
 * @return:
 * @Version: V1.0.0
 * @Copyright:Anhui University
 **/

@Data
public class ScreenDataVO extends Project{

    private int projectCount;   //项目总数量

    private int normalCount;       //一级指标数量 正常
    private int warnCount;     //一级指标数量 警告
    private int dangerCount;       //一级指标数量 危险
    private int offLineCount;     //一级指标数量 离线
    private String warnLogTime;     //警报时间
    private Double currentData;     //指标当前采集的最新数据

    private DataDefineVO dataDefineVO;
    private ProjectMonitor projectMonitor;
    private WarnLog warnLog;
}
