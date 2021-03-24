package com.weida.epmain.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epmain.dto.Customer;
import com.weida.epmain.service.CustomerService;
import com.weida.epcommon.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/getCustomerPage")
        public CommonResult getCustomerPage(int pageNum, int pageSize, String name, String phone)  {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("name", name);
        paramMap.put("phone", phone);
        PageInfo<Customer> data = customerService.getCustomerPage(paramMap);
        return CommonResult.success(data);
    }

    @PostMapping("/editCustomer")
    public CommonResult editCustomer(Customer customer){
        customerService.editCustomer(customer);
        return CommonResult.success("");
    }

    @PostMapping("/addCustomer")
    public CommonResult addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return CommonResult.success("");
    }

    @PostMapping("/delCustomer")
    public CommonResult delCustomer(Integer id){
        customerService.delCustomer(id);
        return CommonResult.success("");
    }

    @RequestMapping("/getCustomers")
    public CommonResult getCustomers()  {
        Map<String, Object> paramMap = Maps.newHashMap();
        List<Customer> data = customerService.getCustomers(paramMap);
        return CommonResult.success(data);
    }


}
