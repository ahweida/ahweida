package com.weida.epuser.mapper;

import com.weida.epuser.dto.UserProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserProject record);

    int insertSelective(UserProject record);

    UserProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserProject record);

    int updateByPrimaryKey(UserProject record);

    List<UserProject> selectUserProjects(@Param("userId")Integer userId);
}