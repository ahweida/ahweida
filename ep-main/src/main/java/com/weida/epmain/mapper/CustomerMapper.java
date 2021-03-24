package com.weida.epmain.mapper;


import com.weida.epmain.dto.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {

    List<Customer> selectCustomers(Map<String, Object> parameters);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}