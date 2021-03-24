package com.weida.epuser.service;

import com.weida.epuser.dto.Dept;
import com.weida.epuser.dto.vo.DeptVo;

import java.util.List;
import java.util.Map;

public interface DeptServise {

    void addDept(Dept dept);

    void delDeptById(Dept dept);

    void editDeptById(Dept record);

    Dept getDeptById(Integer id);

    /**
     * @Description: TODO 获取所有部门树形结构列表
     * @Author: HX
     * @CreateDate: 2021/3/17/12:31
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    List<DeptVo> selectTreeList();

    /**
     * @Description: TODO 根据部门名称获取所有部门列表
     * @Author: HX
     * @CreateDate: 2021/3/17/12:31
     * @param: [dept]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    List<DeptVo> selectDeptList(Dept dept);

    /**
     * @Description: TODO 判断该部门是否有子部门
     * @Author: HX
     * @CreateDate: 2021/3/17/17:04
     * @param:
     * @return: a
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    boolean hasChild(Integer id);
}
