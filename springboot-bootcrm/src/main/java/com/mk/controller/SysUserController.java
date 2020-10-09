package com.mk.controller;

import com.mk.mapper.SysUserMapper;
import com.mk.po.Customer;
import com.mk.po.SysUser;
import com.mk.service.CustomerService;
import com.mk.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //c测试
    @RequestMapping("/test")
    @ResponseBody
    public List<Customer> test(){
        //测试用例
        Customer customer = new Customer();
        customer.setCust_name("小韩");
        //从redis中查看是否有响应的值
        List<Customer> customerList = (List<Customer>) redisTemplate.opsForValue().get("customerList");
        if (null == customerList){      //redis中没有相应的值
            //从mysql数据库中获得相应的数据
            customerList = customerService.selectCustomerList(customer);
            //将数据存放在redis中
            redisTemplate.opsForValue().set("customerList",customerList);
        }
        //List<Customer> customers = customerService.selectCustomerList(customer);
        return customerList;
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
