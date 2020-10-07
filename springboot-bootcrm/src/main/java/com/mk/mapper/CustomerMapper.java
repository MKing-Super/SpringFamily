package com.mk.mapper;

import com.mk.po.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {
    //查询用户的信息
    public List<Customer> selectCustomerList(Customer customer);
    //删除客户信息
    public int deleteCustomer(@Param("cust_id") Integer cust_id);
    //创建用户
    public int createCustomer(Customer customer);
    //修改客户的信息
    public int updateCustomer(Customer customer);
    //通过id取得客户信息
    public Customer findCustomerById(@Param("cust_id") Integer cust_id);
}
