package com.weida.epuser.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.CrmRelationUser;
import com.weida.epuser.dto.Post;
import com.weida.epuser.dto.User;
import com.weida.epuser.mapper.CrmRelationUserMapper;
import com.weida.epuser.mapper.UserMapper;
import com.weida.epuser.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CrmRelationUserMapper crmRelationUserMapper;


    @Override
    public User getUser(User user) {
        return userMapper.selectByAccountAndPwd(user.getAccount(), user.getPassword());
    }

    @Override
    public User getUserByCrmAccount(String crmAccount) {
        CrmRelationUser crmRelationUser = crmRelationUserMapper.selectByCrmAccount(crmAccount);
        if(crmRelationUser == null){
            return null;
        }
        return userMapper.selectByAccount(crmRelationUser.getAccount());
    }


    @Override
    public PageInfo<User> selectUserList(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<User> userList = userMapper.selectUserList((User)paramMap.get("user"));
        return new PageInfo<User>(userList);
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void delUserById(User user) {
        userMapper.delUserById(user);
    }

    @Override
    public void editUserById(User user) {

    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }
}
