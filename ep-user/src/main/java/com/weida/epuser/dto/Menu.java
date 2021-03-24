package com.weida.epuser.dto;

import lombok.Data;

@Data
public class Menu {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer menuType;

    private String route;

    private String menuUrl;

    private String icon;

    private Integer orderNum;

    private String systemCode;

    private String remark;

}