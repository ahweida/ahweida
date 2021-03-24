package com.weida.epmain.service;

import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.dto.CustomTargetDto;
import com.weida.epmain.dto.vo.CenterDataVO;
import java.util.List;
import java.util.Map;

public interface ProjectScreenService {

    Project getProjectInfo(Integer projectId);

    List<Object> getOptions(Integer projectId);

    List<Object> getTables(Integer projectId);

    Map<String, Object> getCenterDatas(Integer projectId);

    Map<String, Object> optionToTable(Integer chartMainId, Integer projectId);

    Object getcustomTargetChart(CustomTargetDto customTargetDto);

    Object getOption(String chartMainId, String hour);

    Object getTable(String chartMainId, String hour);

}
