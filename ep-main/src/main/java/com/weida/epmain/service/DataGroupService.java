package com.weida.epmain.service;

import com.weida.epmain.dto.DataGroup;

import java.util.List;
import java.util.Map;

public interface DataGroupService {

    List<DataGroup> getDataGroups(Map<String, Object> parameters);
}
