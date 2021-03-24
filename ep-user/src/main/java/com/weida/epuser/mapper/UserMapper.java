package com.weida.epuser.mapper;

import com.weida.epuser.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User selectByAccountAndPwd(@Param("account") String account, @Param("password")String password);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByAccount(@Param("account") String account);

    List<User> selectUserList(User user);

    void delUserById(User user);
}