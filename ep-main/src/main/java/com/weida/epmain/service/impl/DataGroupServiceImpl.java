package com.weida.epmain.service.impl;

import com.weida.epmain.dto.DataGroup;
import com.weida.epmain.mapper.DataGroupMapper;
import com.weida.epmain.service.DataGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DataGroupServiceImpl implements DataGroupService {

    @Resource
    private DataGroupMapper dataGroupMapper;

    @Override
    public List<DataGroup> getDataGroups(Map<String, Object> parameters) {
        return dataGroupMapper.selectDataGroups(parameters);
    }
}
