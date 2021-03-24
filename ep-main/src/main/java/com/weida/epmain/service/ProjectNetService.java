package com.weida.epmain.service;

import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.ProjectNet;
import com.weida.epmain.dto.vo.ProjectNetVO;

import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/2/23 13:17
 * @Desc: TODO
 */
public interface ProjectNetService {
    PageInfo<ProjectNetVO> getProjectNetPage(Map<String, Object> paramMap);

    void editProjectNet(ProjectNet projectNet);

    void addProjectNet(ProjectNet projectNet);

    void delProjectNet(Integer id);

    void openStatus(Integer projectId, String name);

    void closeStatus(Integer projectId, String name);

    void updateVersion(Integer projectId, String name,String version);
}
