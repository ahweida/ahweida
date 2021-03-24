package com.weida.epuser.service.impl;

import com.weida.epuser.dto.UserProject;
import com.weida.epuser.mapper.UserProjectMapper;
import com.weida.epuser.service.UserProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/13 10:19
 * @Desc: TODO
 */
@Service
public class UserProjectServiceImpl implements UserProjectService {

    @Resource
    private UserProjectMapper userProjectMapper;


    @Override
    public List<UserProject> getUserProjects(Integer userId) {
        return userProjectMapper.selectUserProjects(userId);
    }
}
