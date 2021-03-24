package com.weida.epmain.service.impl;

import com.weida.epmain.dto.DynamicControl;
import com.weida.epmain.mapper.DynamicControlMapper;
import com.weida.epmain.service.DynamicControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/2/2 上午11:02
 * @version:
 */
@Service
public class DynamicControlServiceImpl implements DynamicControlService {

    @Autowired
    private DynamicControlMapper dynamicControlMapper;


    @Override
    public List<DynamicControl> getDynamicControlInfoByProjectId(Integer projectID) {
        List<DynamicControl> dynamicControls = dynamicControlMapper.selectByProjectId(projectID);
        return dynamicControls;
    }

    @Override
    public Integer deleteProjectAdjustById(Integer id) {
        return dynamicControlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer editProjectAdjust(DynamicControl record) {
        return dynamicControlMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer insertProjectAdjust(DynamicControl record) {
        return dynamicControlMapper.insert(record);
    }
}
