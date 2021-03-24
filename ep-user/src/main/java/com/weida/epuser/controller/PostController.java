package com.weida.epuser.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.Post;
import com.weida.epuser.service.PostServise;
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
@RequestMapping("/postController")
@Slf4j
public class PostController {
    @Autowired
    private PostServise postServise;


    /**
     * @Description: TODO 获取所有未删除岗位
     * @Author: HX
     * @CreateDate: 2021/3/18/13:20
     * @param: [post]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping({"/list",""})
    public CommonResult list(Post post,@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("post", post);
        try {
            PageInfo<Post> postList = postServise.selectPostList(paramMap);
            return CommonResult.success(postList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("查询失败");
        }
    }

    /**
     * @Description: TODO 添加岗位
     * @Author: HX
     * @CreateDate: 2021/3/18/13:20
     * @param: [post]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/addPost")
    public CommonResult addPost(@Valid Post post,BindingResult result){
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            post.setDeleteFlag(0);
            postServise.addPost(post);
            return CommonResult.success("添加岗位成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("添加岗位失败");
        }
    }

    /**
     * @Description: TODO 删除岗位
     * @Author: HX
     * @CreateDate: 2021/3/18/13:20
     * @param: [id]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/delPostById")
    public CommonResult delPostById(@RequestParam("id") Integer id){
        try {
            Post post = new Post();
            post.setId(id);
            post.setDeleteFlag(1);
            postServise.delPostById(post);
            return CommonResult.success("删除岗位成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("删除岗位失败");
        }
    }


    /**
     * @Description: TODO 修改岗位信息
     * @Author: HX
     * @CreateDate: 2021/3/18/13:21
     * @param: [post]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/editPostById")
    public CommonResult editPostById(@Valid Post post, BindingResult result){
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            postServise.editPostById(post);
            return CommonResult.success("编辑岗位成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("编辑岗位失败");
        }
    }

    /**
     * @Description: TODO 根据岗位id获取岗位信息
     * @Author: HX
     * @CreateDate: 2021/3/18/13:21
     * @param: [id]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/getPostById")
    public CommonResult getPostById(@RequestParam("id") Integer id){
        try {
            Post post = postServise.getPostById(id);
            return CommonResult.success(post);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return CommonResult.fail("获取岗位失败");
        }
    }

}
