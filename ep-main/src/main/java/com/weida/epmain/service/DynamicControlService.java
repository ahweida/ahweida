package com.weida.epmain.service;

import com.weida.epmain.dto.DynamicControl;

import java.util.List;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/2 上午11:01
 * @version:
 */
public interface DynamicControlService {

    List<DynamicControl> getDynamicControlInfoByProjectId(Integer projectID);

    Integer deleteProjectAdjustById(Integer id);

    Integer editProjectAdjust(DynamicControl record);

    Integer insertProjectAdjust(DynamicControl record);
}
