package com.mk.controller;

import com.mk.po.Customer;
import com.mk.po.SysUser;
import com.mk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class ListContoller {
    @Autowired
    private CustomerService customerService;

    //去添加页面
    @GetMapping("/toadd")
    public String toadd(){
        return "customer/add";
    }
    //删除客户信息
    @GetMapping("/todelete/{cust_id}")
    public String todelete(@PathVariable("cust_id")Integer cust_id,Model model){
        //System.out.println(cust_id);
        int i = customerService.deleteCustomer(cust_id);
        if (i>0){
            System.out.println("删除成功");
        }
        return "customer/list";
    }
    //去修改页面
    @GetMapping("/toupdate/{cust_id}")
    public String toupdate(@PathVariable("cust_id") int cust_id,Model model){
        //修改时数据回显
        Customer customer = customerService.findCustomerById(cust_id);
        model.addAttribute("customer",customer);
        return "customer/update";
    }
    //查询客户
    @PostMapping(value = "/selectCustomerList")
    public String selectCustomerList(String custName,String cust_source,String cust_industry,String cust_level, Model model){
        Customer customer = new Customer();
        customer.setCust_name(custName);
        customer.setCust_source(cust_source);
        customer.setCust_industry(cust_industry);
        customer.setCust_level(cust_level);

        List<Customer> customers = customerService.selectCustomerList(customer);
        //System.out.println(customers);
        model.addAttribute("customers",customers);
        return "customer/list";
    }

    //添加客户
    @PostMapping("/addcustomer")
    public String createCustomer(Customer customer, HttpSession session){
        //创建人为当前用户
        SysUser sys_user = (SysUser) session.getAttribute("SYS_USER");
        customer.setCust_create_id(sys_user.getUser_id());
        //当前时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        customer.setCust_createtime(timestamp);
        System.out.println(customer);
        int i = customerService.createCustomer(customer);
        if (i>0){
            System.out.println("添加成功");
        }
        return "customer/list";
    }

    //修改客户信息
    @PostMapping("/updatecustomer")
    public String editcustomer(Customer customer,HttpSession session){
        //创建人为当前用户
        SysUser sys_user = (SysUser) session.getAttribute("SYS_USER");
        customer.setCust_create_id(sys_user.getUser_id());

        System.out.println(customer);
        int i = customerService.updateCustomer(customer);
        if (i>0){
            System.out.println("更新成功"+i);
        }
        return "customer/list";
    }
}
