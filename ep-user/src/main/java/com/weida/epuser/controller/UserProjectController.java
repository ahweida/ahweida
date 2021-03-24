package com.weida.epuser.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.UserProject;
import com.weida.epuser.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Auther: zhaofei
 * @Date: 2021/1/13 11:27
 * @Desc: TODO
 */
@RestController
@RequestMapping("/userProject")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping(value = "/getUserProjects")
    public Object getUserProjects(Integer userId){
        List<UserProject> data = userProjectService.getUserProjects(userId);
        return data;
    }



}
