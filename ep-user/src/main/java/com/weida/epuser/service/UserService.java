package com.weida.epuser.service;


import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUser(User user);

    User getUserByCrmAccount(String crmAccount);

    PageInfo<User> selectUserList(Map<String, Object> paramMap);

    void addUser(User user);

    void delUserById(User user);

    void editUserById(User user);

    User getUserById(Integer id);
}