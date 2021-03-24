package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.ExceptionLogVO;
import com.weida.epmain.mapper.ExceptionLogMapper;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.service.ExceptionLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/2/20 10:59
 * @Desc: TODO
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Resource
    private ExceptionLogMapper exceptionLogMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public void addExceptionLog(ExceptionLog exceptionLog) {
        exceptionLogMapper.insert(exceptionLog);
    }

    @Override
    public PageInfo<ExceptionLogVO> getExceptionLogPage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<ExceptionLogVO> exceptionLogs = exceptionLogMapper.selectExceptionLog(paramMap);
        for(ExceptionLogVO exceptionLog : exceptionLogs){
            Project project = projectMapper.selectByPrimaryKey(exceptionLog.getProjectId());
            exceptionLog.setProjectName(project == null ? "" : project.getProjectName());
        }
        return new PageInfo<ExceptionLogVO>(exceptionLogs);
    }
}
