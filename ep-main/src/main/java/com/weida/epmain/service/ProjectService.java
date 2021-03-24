package com.weida.epmain.service;

import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.ProjectVO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    PageInfo<ProjectVO> getProjectPage(Map<String, Object> paramMap);

    void editProject(Project project) throws ParseException;

    void addProject(Project project) throws ParseException;

    void delProject(Integer id);

    void endProject(Integer id);

    List<Project> getProjects(Map<String, Object> paramMap);
}
