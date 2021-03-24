package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epcommon.jwt.JsonUtils;
import com.weida.epcommon.jwt.JwtUtils;
import com.weida.epcommon.jwt.Payload;
import com.weida.epcommon.jwt.UserInfo;
import com.weida.epmain.dto.*;
import com.weida.epmain.dto.vo.*;
import com.weida.epmain.feign.SchedualEpUser;
import com.weida.epmain.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CenterScreenServiceImpl {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ScreenMapper screenMapper;

    @Autowired
    private SchedualEpUser schedualEpUser;

    /**
     * @Description: TODO：获取中心大屏数据
     * @Author: HX
     * @CreateDate: 2021/1/8/12:44
     * @param:
     * @return: a
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    public Map<String,Object> getCenterScreenData(Integer userId){

        /*
        * 中心大屏数据
        * */
        Map<String,Object> screenDataMap = new HashMap<>();
        //项目概况
        List<Integer> projectStatusList = new ArrayList<>();
        //指标概况
        List<Integer> monitorStatusList = new ArrayList<>();
        //项目一级监控指标Map
        Map<String,List<ScreenDataVO>> projectMonitorMap = new LinkedHashMap<>();
        //异常项目
        List<ScreenDataVO> projectErroList = new ArrayList<>();
        //异常项目一级指标
        List<ScreenDataVO> projectErroMonitorList = new ArrayList<>();


        Integer normalCount = 0;       //一级指标数量 正常
        Integer warnCount = 0;     //一级指标数量 警告
        Integer dangerCount = 0;       //一级指标数量 危险
        Integer offLineCount = 0;       //一级指标数量 离线

        Integer normalProjectCount = 0;       //项目数量 正常
        Integer warnProjectCount = 0;     //项目数量 警告
        Integer dangerProjectCount = 0;       //项目数量 危险
        Integer offLineProjectCount = 0;       //项目数量 危险

        //获取当前用户拥有项目数量
        String userProject = schedualEpUser.getUserProjects(userId);
        List<UserProjectVO> userProjectVOS = JsonUtils.toList(userProject, UserProjectVO.class);
        //获取当前用户拥有项目的基本信息
        List<ScreenDataVO> projectList = screenMapper.findProjectList(userProjectVOS);

        //检测指标数和状态
        for (ScreenDataVO project:projectList) {
            int projectStatus = 0;//green


            //查询项目指标标准
            List<ScreenDataVO> projectMonitorList = screenMapper.findProjectMonitorList(project);
            List<ScreenDataVO> endMonitorList = new ArrayList<>();
            projectMonitorMap.put(project.getId()+"",endMonitorList);
            //查询该项目最新各指标数据
            Map<String, Double> projectDataMap = screenMapper.findProjectDataList(project);

            //判断项目是否离线
            if(null != projectDataMap){
                //项目是否有监控内容
                if(projectMonitorList.size()>0){
                    //检测各级指标状态
                    for (ScreenDataVO monitor:projectMonitorList) {
                        DataDefineVO dataDefineVO = monitor.getDataDefineVO();
                        ProjectMonitor projectMonitor = monitor.getProjectMonitor();

                        /*
                         * 各指标数量
                         * */
                        //最新采集数据
                        Double currentData = projectDataMap.get(dataDefineVO.getMysqlField());
                        if(currentData == null){
                            continue;
                        }
//                        if(null != projectDataMap.get(dataDefineVO.getMysqlField())){
//                            currentData = projectDataMap.get(dataDefineVO.getMysqlField());
//                        }
                        monitor.setCurrentData(currentData);
                        if (1==projectMonitor.getLevel()){

                            //指标状态判断
                            if((projectMonitor.getNormalMin() <= currentData)&& (currentData < projectMonitor.getNormalMax())){
                                normalCount++;
                                monitor.setStatus(0);//green
                            }else if((projectMonitor.getWarnMin() <= currentData )&& (currentData < projectMonitor.getWarnMax())){

                                warnCount++;

                                //异常指标状态
                                monitor.setStatus(1);//yellow
                                //项目出现的状态
                                projectStatus = projectStatus < 2 ? 1:projectStatus;
                                projectErroMonitorList.add(monitor);
                                endMonitorList.add(monitor);
                            }else {
                                dangerCount++;
                                //异常指标状态
                                monitor.setStatus(2);//red
                                //项目出现的状态
                                projectStatus = 2;
                                projectErroMonitorList.add(monitor);
                                endMonitorList.add(monitor);
                            }

                        }else if (2 == projectMonitor.getLevel()){//预留接口
                           //secondCount++;
                        }
                    }
                }
            }else {
                projectStatus = 3;//grey离线
                offLineCount = offLineCount+projectMonitorList.size();
            }

            //项目状态
            project.setStatus(projectStatus);
            if(projectStatus==0){
                normalProjectCount++;
            }else if(projectStatus==1){
                warnProjectCount++;
                projectErroList.add(project);
            }else if(projectStatus==2){
                dangerProjectCount++;
                projectErroList.add(project);
            }else {
                offLineProjectCount++;
                projectErroList.add(project);
            }
        }

        projectStatusList.add(normalProjectCount);
        projectStatusList.add(warnProjectCount);
        projectStatusList.add(dangerProjectCount);
        projectStatusList.add(offLineProjectCount);

        monitorStatusList.add(normalCount);
        monitorStatusList.add(warnCount);
        monitorStatusList.add(dangerCount);
        monitorStatusList.add(offLineCount);

        screenDataMap.put("projectStatusList",projectStatusList);
        screenDataMap.put("monitorStatusList",monitorStatusList);
        screenDataMap.put("projectList",projectList);
        screenDataMap.put("projectMonitorMap",projectMonitorMap);
        screenDataMap.put("projectErroList",projectErroList);
        screenDataMap.put("projectErroMonitorList",projectErroMonitorList);
        return screenDataMap;
    }


    /**
     * @Description: TODO 中心大屏历史警告预览
     * @Author: HX
     * @CreateDate: 2021/1/27/16:27
     * @param:
     * @return: a     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    public List<ScreenDataVO> getCenterScreenHistoryData(String startDate,String endDate, Integer userId) {

        //所有项目的历史警告记录
        List<ScreenDataVO> projectWarnLogList = new ArrayList<>();
        //获取当前用户拥有项目数量
        String userProject = schedualEpUser.getUserProjects(userId);
        List<UserProjectVO> userProjectVOS = JsonUtils.toList(userProject, UserProjectVO.class);

        //获取当前用户拥有项目的基本信息
        List<ScreenDataVO> projectList = screenMapper.findProjectList(userProjectVOS);

        for (ScreenDataVO project:projectList) {
            List<ScreenDataVO> warnLogDataList = screenMapper.findWarnLogData(project.getId(),startDate,endDate);

            if(warnLogDataList.size()>0){//有历史记录警告项目
                for (ScreenDataVO warnLog:warnLogDataList) {
                    if(warnLog.getWarnLog().getWarnType()==1){
                        project.setWarnCount(warnLog.getWarnCount());
                    }else{
                        project.setDangerCount(warnLog.getWarnCount());
                    }
                }
                projectWarnLogList.add(project);
            }
        }

        return projectWarnLogList;
    }


    /**
     * @Description: TODO 历史警告详情
     * @Author: HX
     * @CreateDate: 2021/1/27/16:27
     * @param:
     * @return: a     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/

    public PageInfo<ScreenDataVO> getProjectWarnLogDetail(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<ScreenDataVO> warnLogDetailList = screenMapper.findWarnLogDetail(paramMap);
        return new PageInfo<ScreenDataVO>(warnLogDetailList);
    }

    /**
     * @Description: TODO  项目历史警告预览
     * @Author: HX
     * @CreateDate: 2021/1/27/16:27
     * @param:
     * @return: a
    * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    public ScreenDataVO getProjectScreenHistoryData(String projectId,String startDate,String endDate) {

        //获取当前用户拥有项目的基本信息
        ScreenDataVO projectMsg = screenMapper.findProjectMsg(projectId);
        List<ScreenDataVO> warnLogDataList = screenMapper.findWarnLogData(projectMsg.getId(),startDate,endDate);
        if(warnLogDataList.size()>0){
            for (ScreenDataVO warnLog:warnLogDataList) {
                if(warnLog.getWarnLog().getWarnType()==1){
                    projectMsg.setWarnCount(warnLog.getWarnCount());
                }else{
                    projectMsg.setDangerCount(warnLog.getWarnCount());
                }
            }
        }else {
            projectMsg.setWarnCount(0);
            projectMsg.setDangerCount(0);
        }
        return projectMsg;
    }

    /**
     * @Description: TODO 获取警告指标类型
     * @Author: HX
     * @CreateDate: 2021/1/27/16:28
     * @param:
     * @return: a
    * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    public List<DataDefineVO> getDataNameOptions(String projectId, String warnType) {
        List<DataDefineVO> dataNameOptionList = screenMapper.getDataNameOptions(projectId,warnType);
        return dataNameOptionList;
    }



    /**
     * @Description: TODO 获取用户信息
     * @Author: HX
     * @CreateDate: 2021/1/16/16:28
     * @param:
     * @return: a
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/

    public Payload<UserInfo> getUserInfo(HttpServletRequest request){
        String accessToken = request.getHeader(JwtUtils.AUTH_HEADER_KEY);
        Payload<UserInfo> infoFromToken = null;
        try {
            infoFromToken = JwtUtils.getInfoFromToken(accessToken, UserInfo.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return infoFromToken;
    }

    public static void main(String[] args) {
        String ksy = "2020-12-28 09:35:27";

        Double a = (Double) null;
        System.out.println(ksy.substring(0,10));


        Integer day = 1;
        String startDate = "";
        String endDate = "";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        endDate = sdf.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MINUTE,-120);
        startDate = sdf.format(calendar.getTime());
        System.out.println(startDate);
        System.out.println(endDate);

    }



}
