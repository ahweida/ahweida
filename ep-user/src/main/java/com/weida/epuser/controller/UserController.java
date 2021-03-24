package com.weida.epuser.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.User;
import com.weida.epuser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userController")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * @Description: TODO 获取所有未删除用户
     * @Author: HX
     * @CreateDate: 2021/3/18/13:20
     * @param: [user]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping({"/list",""})
    public CommonResult list(User user,@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("user", user);
        PageInfo<User> userList = userService.selectUserList(paramMap);
        return CommonResult.success(userList);
    }

    /**
     * @Description: TODO 添加用户
     * @Author: HX
     * @CreateDate: 2021/3/18/13:20
     * @param: [user]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/addUser")
    public CommonResult addUser(@Valid User user,BindingResult result){
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            userService.addUser(user);
            return CommonResult.success("添加用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("添加用户失败");
        }
    }

    /**
     * @Description: TODO 删除用户
     * @Author: HX
     * @CreateDate: 2021/3/18/13:20
     * @param: [id]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/delUserById")
    public CommonResult delUserById(@RequestParam("id") Integer id){
        try {
            User user = new User();
            user.setId(id);
            user.setStatus(2);
            userService.delUserById(user);
            return CommonResult.success("删除用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("删除用户失败");
        }
    }


    /**
     * @Description: TODO 修改用户信息
     * @Author: HX
     * @CreateDate: 2021/3/18/13:21
     * @param: [user]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/editUserById")
    public CommonResult editUserById(@Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            userService.editUserById(user);
            return CommonResult.success("编辑用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("编辑用户失败");
        }
    }

    /**
     * @Description: TODO 根据用户id获取用户信息
     * @Author: HX
     * @CreateDate: 2021/3/18/13:21
     * @param: [id]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/getUserById")
    public CommonResult getUserById(@RequestParam("id") Integer id){
        try {
            User user = userService.getUserById(id);
            return CommonResult.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("获取用户失败");
        }
    }

}
