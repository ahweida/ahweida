package com.weida.epmain.dto;

import lombok.Data;

@Data
public class DataGroup {
    private Integer id;

    private String name;

    private String ip;

    private String port;

    private Integer protocol;

    private String userName;

    private String password;

    private Integer projectId;

}