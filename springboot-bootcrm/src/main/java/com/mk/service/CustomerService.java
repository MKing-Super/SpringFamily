package com.mk.service;

import com.mk.po.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerService {
    //查询用户的信息
    public List<Customer> selectCustomerList(Customer customer);
    //删除客户信息
    public int deleteCustomer(Integer cust_id);
    //创建用户
    public int createCustomer(Customer customer);
    //修改客户的信息
    public int updateCustomer(Customer customer);
    //通过id取得客户信息
    public Customer findCustomerById(Integer cust_id);
}
