package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.Customer;
import com.weida.epmain.mapper.CustomerMapper;
import com.weida.epmain.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public PageInfo<Customer> getCustomerPage(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageNum"), (int)paramMap.get("pageSize"));
        List<Customer> customers = customerMapper.selectCustomers(paramMap);
        return new PageInfo<Customer>(customers);
    }

    @Override
    public void editCustomer(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerMapper.insert(customer);
    }

    @Override
    public void delCustomer(Integer id) {
        customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Customer> getCustomers(Map<String, Object> paramMap) {
        return  customerMapper.selectCustomers(paramMap);
    }
}
