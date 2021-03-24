package com.weida.epuser.service.impl;


import com.weida.epuser.dto.Dept;
import com.weida.epuser.dto.vo.DeptVo;
import com.weida.epuser.mapper.DeptMapper;
import com.weida.epuser.service.DeptServise;
import com.weida.epuser.util.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptServise {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public void addDept(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public void delDeptById(Dept dept) {
        deptMapper.delDeptById(dept);
    }

    @Override
    public void editDeptById(Dept record) {
        deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DeptVo> selectTreeList() {
        List<DeptVo> deptList = deptMapper.selectTreeList();
        List<DeptVo> treeDeptList = null;
        try {
            treeDeptList = TreeUtils.buildByRecursive(deptList,"Id", "ParentId", "Children");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return treeDeptList;
    }

    @Override
    public List<DeptVo> selectDeptList(Dept dept) {
        List<DeptVo> deptList = deptMapper.selectDeptList(dept);
        return deptList;
    }

    @Override
    public boolean hasChild(Integer id) {
        List<Dept> childList = deptMapper.hasChild(id);
        if(childList !=null && childList.size()>0){
            return true;
        }else {
            return false;
        }
    }
}
