package com.weida.epmain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.ProjectMonitor;
import com.weida.epmain.dto.WarnLog;
import com.weida.epmain.mapper.DataDefineMapper;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.mapper.ProjectMonitorMapper;
import com.weida.epmain.mapper.WarnLogMapper;
import com.weida.epmain.service.WarnLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: zhaof
 * @Date: 2021/1/22 13:21
 * @Desc: TODO
 */
@Service
public class WarnLogServiceImpl implements WarnLogService {

    @Resource
    private ProjectMonitorMapper projectMonitorMapper;

    @Resource
    private DataDefineMapper dataDefineMapper;

    @Resource
    private WarnLogMapper warnLogMapper;

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/22 13:18
     * @Desc: 服务端接受采集数据的消息后，根据监控的指标范围判断是否超标，添加超标记录
     */
    @Override
    public void saveWarnLog(JSONObject dataJson) {
        Map<String, Object> parameters = Maps.newHashMap();
        Integer projectId = dataJson.getInteger("projectId");
        parameters.put("projectId", projectId);
        List<ProjectMonitor> projectMonitors = projectMonitorMapper.selectProjectMonitors(parameters);
        List<DataDefine> dataDefines = dataDefineMapper.selectDefines(parameters);
        Map<Integer, DataDefine> defineMap = dataDefines.stream().collect(Collectors.toMap(DataDefine::getId, item -> item));
        for(ProjectMonitor projectMonitor : projectMonitors){
            Double dataValue =  dataJson.getDouble(defineMap.get(projectMonitor.getDataDefineId()).getMysqlField());
            Double normalMin = projectMonitor.getNormalMin();
            Double normalMax = projectMonitor.getNormalMax();
            Double warnMin = projectMonitor.getWarnMin();
            Double warnMax = projectMonitor.getWarnMax();
            if(dataValue >= normalMin && dataValue < normalMax){
                //正常情况，不添加警告日志
            }else if(dataValue >= warnMin && dataValue < warnMax){
                WarnLog warnLog = new WarnLog();
                warnLog.setId(dataJson.getInteger("id"));
                warnLog.setProjectId(projectId);
                warnLog.setDataDefineId(projectMonitor.getDataDefineId());
                warnLog.setCollectTime(dataJson.getTimestamp("collectTime"));
                //warnLog.setCollectTime(DateUtil.parase(data.get("collectTime").toString(), DateUtil.DATE_FORMAT_SECOND));
                warnLog.setWarnType(1);//1表示警告 2 表示超标
                warnLog.setDataValue(dataValue);
                warnLog.setNormalMin(normalMin);
                warnLog.setNormalMax(normalMax);
                warnLogMapper.insert(warnLog);
            }else{
                WarnLog warnLog = new WarnLog();
                warnLog.setId(dataJson.getInteger("id"));
                warnLog.setProjectId(projectId);
                warnLog.setDataDefineId(projectMonitor.getDataDefineId());
                warnLog.setCollectTime(dataJson.getTimestamp("collectTime"));
                //warnLog.setCollectTime(DateUtil.parase(data.get("collectTime").toString(), DateUtil.DATE_FORMAT_SECOND));
                warnLog.setWarnType(2);//1表示警告 2 表示超标
                warnLog.setDataValue(dataValue);
                warnLog.setNormalMin(normalMin);
                warnLog.setNormalMax(normalMax);
                warnLogMapper.insert(warnLog);
            }
        }
    }
}
