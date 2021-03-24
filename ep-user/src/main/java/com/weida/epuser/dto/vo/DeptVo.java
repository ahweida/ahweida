package com.weida.epuser.dto.vo;

import com.weida.epuser.dto.Dept;
import lombok.Data;

import java.util.List;

@Data
public class DeptVo extends Dept{
    private List<DeptVo> children;
}
