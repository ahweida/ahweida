package com.weida.epmain.service;

import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    PageInfo<Customer> getCustomerPage(Map<String, Object> paramMap);

    void editCustomer(Customer customer);

    void addCustomer(Customer customer);

    void delCustomer(Integer id);

    List<Customer> getCustomers(Map<String, Object> paramMap);
}
