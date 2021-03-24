package com.weida.epuser.dto.vo;

import com.weida.epuser.dto.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo extends Menu {
    private List<MenuVo> children;
}
