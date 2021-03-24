package com.weida.epmain.mapper;


import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.ProjectMonitor;
import com.weida.epmain.dto.vo.DataDefineVO;
import com.weida.epmain.dto.vo.ScreenDataVO;
import com.weida.epmain.dto.vo.UserProjectVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ScreenMapper {

    List<ScreenDataVO> findProjectList(@Param("list") List<UserProjectVO> userProjectVOS);

    List<ScreenDataVO> findProjectMonitorList(Project project);

    Map<String, Double> findProjectDataList(Project project);

    List<ScreenDataVO> findWarnLogData(@Param("projectId") Integer projectId,@Param("startDate") String startDate,@Param("endDate")  String endDate);

    List<ScreenDataVO> findWarnLogDetail(Map<String, Object> paramMap);

    ScreenDataVO findProjectMsg(@Param("projectId")String projectId);

    List<DataDefineVO> getDataNameOptions(@Param("projectId")String projectId, @Param("warnType")String warnType);
}