package com.weida.epuser.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CrmRelationUser {
    private Integer id;

    @NotBlank(message = "crm账号不能为空")
    private String crmAccount;

    @NotBlank(message = "关联账号不能为空")
    private String account;

    @NotNull(message = "账号状态不能为空")
    private Integer status;

}