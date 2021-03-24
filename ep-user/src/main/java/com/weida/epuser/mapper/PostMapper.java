package com.weida.epuser.mapper;

import com.weida.epuser.dto.Post;

import java.util.List;
import java.util.Map;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> selectPostList(Post post);

    void delPostById(Post post);
}