package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.DataGroup;
import com.weida.epmain.dto.vo.DataDefineVO;
import com.weida.epmain.mapper.DataDefineMapper;
import com.weida.epmain.mapper.DataGroupMapper;
import com.weida.epmain.service.DataDefineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DataDefineServiceImpl implements DataDefineService {

    @Resource
    private DataDefineMapper dataDefineMapper;

    @Resource
    private DataGroupMapper dataGroupMapper;

    @Override
    public PageInfo<DataDefineVO> getDefinePage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<DataDefineVO> dataDefines = dataDefineMapper.selectDataDefines(paramMap);
        return new PageInfo<DataDefineVO>(dataDefines);
    }

    @Override
    public void editDataDefine(DataDefine dataDefine) {
        DataGroup dataGroup = dataGroupMapper.selectByPrimaryKey(dataDefine.getCollectionGroupId());
        //设置冗余字段
        dataDefine.setProjectId(dataGroup.getProjectId());
        dataDefineMapper.updateByPrimaryKeySelective(dataDefine);
    }

    @Override
    public void addDataDefine(DataDefine dataDefine) {
        DataGroup dataGroup = dataGroupMapper.selectByPrimaryKey(dataDefine.getCollectionGroupId());
        //设置冗余字段
        dataDefine.setProjectId(dataGroup.getProjectId());
        dataDefineMapper.insert(dataDefine);
    }

    @Override
    public void delDataDefine(Integer id) {
        dataDefineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<DataDefine> getDataDefines(Map<String, Object> paramMap) {
        return dataDefineMapper.selectDefinesWithMysqlNotNull(paramMap);
    }
}
