package com.weida.epmain.service;

import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.DataDefine;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.DataDefineVO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface DataDefineService {

    PageInfo<DataDefineVO> getDefinePage(Map<String, Object> paramMap);

    void editDataDefine(DataDefine dataDefine);

    void addDataDefine(DataDefine dataDefine);

    void delDataDefine(Integer id);

    List<DataDefine> getDataDefines(Map<String, Object> paramMap);

}
