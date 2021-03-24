package com.weida.epuser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.Dept;
import com.weida.epuser.dto.Post;
import com.weida.epuser.mapper.PostMapper;
import com.weida.epuser.service.PostServise;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostServise {

    @Resource
    private PostMapper postMapper;

    @Override
    public void addPost(Post post) {
        postMapper.insert(post);
    }

    @Override
    public void delPostById(Post post) {
        postMapper.delPostById(post);
    }

    @Override
    public void editPostById(Post record) {
        postMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Post getPostById(Integer id) {
        return postMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Post> selectPostList(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<Post> postList = postMapper.selectPostList((Post)paramMap.get("post"));
        return new PageInfo<Post>(postList);
    }
}
