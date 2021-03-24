package com.weida.epmain.task;

import com.google.common.collect.Maps;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.Project;
import com.weida.epmain.mapper.DataMapper;
import com.weida.epmain.mapper.ExceptionLogMapper;
import com.weida.epmain.mapper.ProjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/2/19 15:08
 * @Desc: TODO
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StaticScheduleTask {

    @Resource
    private DataMapper dataMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ExceptionLogMapper exceptionLogMapper;

    //3.添加定时任务
    @Scheduled(cron = "0 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        Date now = new Date();
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("status", 1);
        List<Project> projects = projectMapper.selectProjects(parameters);
        List<Map<String, Object>> maps = dataMapper.selectLastCollectTime();
        Map<Integer, Date> map = Maps.newHashMap();
        maps.forEach(a ->{
            map.put((Integer)a.get("projectId"), (Date)a.get("collectTime"));
        });

        for (Project project : projects){
            Date collectTime =  map.get(project.getId());
            Integer offlineTime = project.getOfflineTime();
            boolean isOverTime = collectTime == null || offlineTime * 1000 < now.getTime() - collectTime.getTime();
            boolean isOnLine = project.getOfflineStatus() != null  && project.getOfflineStatus() == 1;
            //如果超时且在线，插入离线记录，项目状态修改为离线
            if(isOverTime && isOnLine){
                ExceptionLog exceptionLog = new ExceptionLog();
                exceptionLog.setProjectId(project.getId());
                exceptionLog.setCreateTime(now);
                exceptionLog.setDescription("离线");
                exceptionLogMapper.insert(exceptionLog);
                //0-离线  1-在线
                project.setOfflineStatus(0);
                projectMapper.updateByPrimaryKeySelective(project);
            }
            //如果不超时且离线，插入上线记录记录，项目状态修改为在线
            if(!isOverTime && !isOnLine){
                ExceptionLog exceptionLog = new ExceptionLog();
                exceptionLog.setProjectId(project.getId());
                exceptionLog.setCreateTime(now);
                exceptionLog.setDescription("上线");
                exceptionLogMapper.insert(exceptionLog);

                //0-离线  1-在线
                project.setOfflineStatus(1);
                projectMapper.updateByPrimaryKeySelective(project);
            }

        }
    }
}
