package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.ProjectNet;
import com.weida.epmain.dto.vo.ProjectNetVO;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.mapper.ProjectNetMapper;
import com.weida.epmain.service.ProjectNetService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/2/23 13:04
 * @Desc: TODO
 */
@Service
public class ProjectNetServiceImpl implements ProjectNetService {

    @Resource
    private ProjectNetMapper projectNetMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageInfo<ProjectNetVO> getProjectNetPage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<ProjectNetVO> projectNets = projectNetMapper.selectList(paramMap);
        for(ProjectNetVO projectNetVO : projectNets){
            Project project = projectMapper.selectByPrimaryKey(projectNetVO.getProjectId());
            projectNetVO.setProjectName(project == null ? "" : project.getProjectName());
        }
        return new PageInfo<>(projectNets);
    }

    @Override
    public void editProjectNet(ProjectNet projectNet)  {
        projectNetMapper.updateByPrimaryKeySelective(projectNet);
        }

    @Override
    public void addProjectNet(ProjectNet projectNet) {
        projectNet.setStatus(0);
        projectNetMapper.insert(projectNet);
    }

    @Override
    public void delProjectNet(Integer id) {
        projectNetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void openStatus(Integer projectId, String name) {
        ProjectNet projectNet = new ProjectNet();
        projectNet.setProjectId(projectId);
        projectNet.setName(name);
        projectNet.setStatus(0);
        projectNetMapper.updateStatus(projectNet);
    }

    @Override
    public void closeStatus(Integer projectId, String name) {
        ProjectNet projectNet = new ProjectNet();
        projectNet.setProjectId(projectId);
        projectNet.setName(name);
        projectNet.setStatus(1);
        projectNetMapper.updateStatus(projectNet);
    }

    @Override
    public void updateVersion(Integer projectId, String name, String version) {
        ProjectNet projectNet = new ProjectNet();
        projectNet.setProjectId(projectId);
        projectNet.setName(name);
        projectNet.setVersion(version);
        projectNetMapper.updateVersion(projectNet);
    }
}
