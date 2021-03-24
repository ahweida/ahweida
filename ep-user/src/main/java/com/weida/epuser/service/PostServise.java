package com.weida.epuser.service;

import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.Dept;
import com.weida.epuser.dto.Post;
import com.weida.epuser.dto.vo.DeptVo;

import java.util.List;
import java.util.Map;

public interface PostServise {

    void addPost(Post post);

    void delPostById(Post post);

    void editPostById(Post record);

    Post getPostById(Integer id);

    PageInfo<Post> selectPostList(Map<String, Object> paramMap);
}
