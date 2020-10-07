package com.mk.service.impl;

import com.mk.mapper.CustomerMapper;
import com.mk.po.Customer;
import com.mk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    //查询用户的信息
    @Override
    public List<Customer> selectCustomerList(Customer customer) {
        return customerMapper.selectCustomerList(customer);
    }
    //删除客户信息
    @Override
    public int deleteCustomer(Integer cust_id) {
        return customerMapper.deleteCustomer(cust_id);
    }
    //创建用户
    @Override
    public int createCustomer(Customer customer) {
        return customerMapper.createCustomer(customer);
    }
    //修改客户的信息
    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }
    //通过id查询客户
    @Override
    public Customer findCustomerById(Integer cust_id) {
        return customerMapper.findCustomerById(cust_id);
    }
}
