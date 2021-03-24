package com.weida.epuser.service;

import com.github.pagehelper.PageInfo;
import com.weida.epuser.dto.CrmRelationUser;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/3/8 10:05
 * @Desc: TODO
 */
public interface CrmRelationUserService {

    PageInfo<CrmRelationUser> getCrmRelationUserPage(Map<String, Object> paramMap);

    void editCrmRelationUser(CrmRelationUser crmRelationUser);

    void addCrmRelationUser(CrmRelationUser crmRelationUser);

    void delCrmRelationUser(Integer id);

}
