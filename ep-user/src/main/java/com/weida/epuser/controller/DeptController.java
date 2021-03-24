package com.weida.epuser.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epuser.dto.Dept;
import com.weida.epuser.dto.vo.DeptVo;
import com.weida.epuser.service.DeptServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deptController")
@Slf4j
public class DeptController {

    @Autowired
    private DeptServise deptServise;

    /**
     * @Description: TODO 获取所有部门列表[树形结构]
     * @Author: HX
     * @CreateDate: 2021/3/17/12:30
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @GetMapping("/treeList")
    public CommonResult treeList(){
        List<DeptVo> deptTreeList = null;
        try {
            deptTreeList = deptServise.selectTreeList();
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("查询失败");
        }
        return CommonResult.success(deptTreeList);
    }

    /**
     * @Description: TODO 根据部门名称获取所有部门
     * @Author: HX
     * @CreateDate: 2021/3/17/12:30
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping({"/list",""})
    public CommonResult list(Dept dept){
        List<DeptVo> deptList = null;
        try {
            deptList = deptServise.selectDeptList(dept);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("查询失败");
        }
        return CommonResult.success(deptList);
    }

    /**
     * @Description: TODO 新增部门
     * @Author: HX
     * @CreateDate: 2021/3/17/16:28
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/addDept")
    public CommonResult addDept(@Valid Dept dept, BindingResult result){
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            dept.setDeleteFlag(0);
            deptServise.addDept(dept);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("添加部门失败");
        }
        return CommonResult.success("");
    }

    /**
     * @Description: TODO 根据id删除部门
     * @Author: HX
     * @CreateDate: 2021/3/17/16:28
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/delDeptById")
    public CommonResult delDeptById(@RequestParam("id") Integer id){
        try {
            Dept dept = new Dept();
            dept.setId(id);
            dept.setDeleteFlag(1);

            //逻辑删除
            deptServise.delDeptById(dept);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("删除部门失败");
        }
        return CommonResult.success("");
    }


    /**
     * @Description: TODO 修改部门信息
     * @Author: HX
     * @CreateDate: 2021/3/17/16:28
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/editDeptById")
    public CommonResult editDeptById(@Valid Dept dept,BindingResult result){
        if (result.hasErrors()) {
            return CommonResult.fail(result.getFieldError().getDefaultMessage());
        }
        try {
            deptServise.editDeptById(dept);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("编辑部门失败");
        }
        return CommonResult.success("");
    }

    /**
     * @Description: TODO 根据id查询部门信息
     * @Author: HX
     * @CreateDate: 2021/3/17/16:28
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/getDeptById")
    public CommonResult getDeptById(@RequestParam("id") Integer id){
        Dept dept = new Dept();
        try {
            dept = deptServise.getDeptById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("获取部门失败");
        }
        return CommonResult.success(dept);
    }


    /**
     * @Description: TODO 判断该部门是否有子部门
     * @Author: HX
     * @CreateDate: 2021/3/17/17:11
     * @param: [id]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @PostMapping("/hasChild")
    public CommonResult hasChild(@RequestParam("id") Integer id){
        try {
            boolean flag = deptServise.hasChild(id);
            return CommonResult.success(flag);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("查询失败");
        }
    }
}
