package com.weida.epuser.service;

import com.weida.epuser.dto.UserProject;

import java.util.List;

public interface UserProjectService {

    List<UserProject> getUserProjects(Integer userId);

}