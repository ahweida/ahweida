package com.weida.epuser.mapper;

import com.weida.epuser.dto.Dept;
import com.weida.epuser.dto.vo.DeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<DeptVo> selectTreeList();

    List<DeptVo> selectDeptList(Dept dept);

    void delDeptById(Dept dept);

    List<Dept> hasChild(@Param("id")Integer id);
}