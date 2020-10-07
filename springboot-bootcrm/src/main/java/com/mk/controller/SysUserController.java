package com.mk.controller;

import com.mk.mapper.SysUserMapper;
import com.mk.po.Customer;
import com.mk.po.SysUser;
import com.mk.service.CustomerService;
import com.mk.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sysuser")
public class SysUserController {
    //依赖注入
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/test")
    @ResponseBody
    public List<Customer> test(){
        Customer customer = new Customer();
        customer.setCust_name("小韩");

        List<Customer> customers = customerService.selectCustomerList(customer);
        return customers;
    }

    //用户登陆
    @RequestMapping(value = "/login")
    public String login(@RequestParam("user_code") String user_code,
                        @RequestParam("user_password") String user_password,
                        HttpSession session, Model model){
        System.out.println(user_code);
        System.out.println(user_password);
        SysUser sysUser = sysUserService.querySysUserByUserid(user_code, user_password);
        if (sysUser != null){
            //将用户对象添加到session
            session.setAttribute("SYS_USER",sysUser);
            //跳转到主页面
            return "customer";
        }
        model.addAttribute("msg","账号或密码错误，请重新输入！");
        //返回登陆页面
        return "login";
    }

    //用户退出
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

}
