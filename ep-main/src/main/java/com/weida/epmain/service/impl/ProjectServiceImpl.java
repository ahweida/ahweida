package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.Customer;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.ProjectVO;
import com.weida.epmain.mapper.CustomerMapper;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.service.ProjectService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public PageInfo<ProjectVO> getProjectPage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<ProjectVO> projects = projectMapper.selectList(paramMap);
        for(ProjectVO projectVO : projects){
            Customer customer = customerMapper.selectByPrimaryKey(projectVO.getCustomerId());
            projectVO.setCustomerName(customer == null ? "" : customer.getName());
        }
        return new PageInfo<>(projects);
    }

    @Override
    public void editProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void addProject(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public void delProject(Integer id) {
        projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void endProject(Integer id) {
        Project project = new Project();
        project.setId(id);
        project.setStatus(2);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<Project> getProjects(Map<String, Object> paramMap) {
        return projectMapper.selectProjects(paramMap);
    }
}
