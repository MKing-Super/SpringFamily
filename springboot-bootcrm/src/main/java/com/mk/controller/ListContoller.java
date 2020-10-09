package com.mk.controller;

import com.mk.po.Customer;
import com.mk.po.SysUser;
import com.mk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ListContoller {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //去添加页面
    @GetMapping("/toadd")
    public String toadd(){
        return "customer/add";
    }
    //删除客户信息
    @GetMapping("/todelete/{cust_id}")
    public String todelete(@PathVariable("cust_id")Integer cust_id,Model model){
        //未修改前的信息
        Customer customer = customerService.findCustomerById(cust_id);
        //redis中的名称
        String redisCustomerName =  customer.getCust_source()+
                customer.getCust_industry()+
                customer.getCust_level();

        Customer customerList = new Customer();
        customerList.setCust_source(customer.getCust_source());
        customerList.setCust_industry(customer.getCust_industry());
        customerList.setCust_level(customer.getCust_level());
        Set<String> keys = redisTemplate.keys(redisCustomerName);

        if (!keys.isEmpty()){       //不为空，有相应的key，说明查询的次数较多，要及时的更新redis中的数据
            //删除
            int i = customerService.deleteCustomer(cust_id);

            if (i>0){
                System.out.println("删除成功");
                //将数据存在redis中
                redisTemplate.opsForValue().set(redisCustomerName,customerService.selectCustomerList(customerList));
            }

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

    /*
    * 查询客户
    * 当需要同过客户来源、客户行业、客户等级来批量查询时，需要进行redis缓存
    * 查询时，如果redis中没有相应的key，那么就需要在redis中插入一条信息
    * 如果redis中有相应的key，那么就直接通过redis查询，不用涉及mysql数据库，查询效率更高
    * */
    //查询客户
    @PostMapping(value = "/selectCustomerList")
    public String selectCustomerList(String custName,String cust_source,String cust_industry,String cust_level, Model model){

        if ((custName != null)&&(custName != "")){      //输入了具体的客户姓名，不是批量操作，不在redis中存储
            Customer customer = new Customer();
            customer.setCust_name(custName);
            customer.setCust_source(cust_source);
            customer.setCust_industry(cust_industry);
            customer.setCust_level(cust_level);
            List<Customer> customers = customerService.selectCustomerList(customer);
            model.addAttribute("customers",customers);
        }else {     //这是批量删除，需要在redis中存储
            Customer customer = new Customer();
            customer.setCust_source(cust_source);
            customer.setCust_industry(cust_industry);
            customer.setCust_level(cust_level);
            //存在redis中的名称
            String redisCustomerName = cust_source+cust_industry+cust_level;
            //从redis中查找
            List<Customer> customers = (List<Customer>) redisTemplate.opsForValue().get(redisCustomerName);
            if (null == customers){ //redis中没有
                //从mysql中查找
                customers = customerService.selectCustomerList(customer);
                if (customers != null){     //mysql中存在数据
                    //将数据存在redis中
                    redisTemplate.opsForValue().set(redisCustomerName,customers);
                }
            }
            model.addAttribute("customers",customers);
        }
        return "customer/list";
    }

    /*
    * 添加客户
    * 添加时，要在redis中修改一条信息（如果redis中存在这条信息的话）
    * 在数据库添加信息完成后，将新信息更新到redis中
    * */
    //添加客户（需要修改redis中的数据）
    @PostMapping("/addcustomer")
    public String createCustomer(Customer customer, HttpSession session){
        String cust_name = customer.getCust_name();
        String cust_source = customer.getCust_source();
        String cust_industry = customer.getCust_industry();
        String cust_level = customer.getCust_level();
        Customer customerList = new Customer();
        customerList.setCust_source(cust_source);
        customerList.setCust_industry(cust_industry);
        customerList.setCust_level(cust_level);
        //存在redis中的名称
        String redisCustomerName = cust_source+cust_industry+cust_level;
        System.out.println(redisCustomerName);
        Set<String> keys = redisTemplate.keys(redisCustomerName);
        System.out.println(keys);
        if ( !keys.isEmpty()  ){       //不为空
            System.out.println("key不为空,需要修改value的属性"+keys);
            //创建人为当前用户
            SysUser sys_user = (SysUser) session.getAttribute("SYS_USER");
            customer.setCust_create_id(sys_user.getUser_id());
            //当前时间
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            customer.setCust_createtime(timestamp);

            int i = customerService.createCustomer(customer);
            if (i>0){
                System.out.println("添加成功");
                //将数据存在redis中
                redisTemplate.opsForValue().set(redisCustomerName,customerService.selectCustomerList(customerList));
            }

        }else{      //在redis中没有key，说明查询不频繁，所以不用在redis中添加
            System.out.println("为空");
            //创建人为当前用户
            SysUser sys_user = (SysUser) session.getAttribute("SYS_USER");
            customer.setCust_create_id(sys_user.getUser_id());
            //当前时间
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            customer.setCust_createtime(timestamp);
            //System.out.println(customer);
            int i = customerService.createCustomer(customer);
            if (i>0){
                System.out.println("添加成功");
            }
        }
        return "customer/list";
    }

    /*
    * 修改客户信息
    * 修改时，需要修改redis中的两条数据（如果redis中存在的话）
    * 首先是将数据库中刚刚修改后的信息，在redis中更新
    * 让后是将还未修改数据库之前的信息，在redis中更新
    * */
    //修改客户信息
    @PostMapping("/updatecustomer")
    public String editcustomer(Customer customer,HttpSession session){
        //未修改前的信息
        Customer customerOld = customerService.findCustomerById(customer.getCust_id());
        Customer customerListOld = new Customer();
        customerListOld.setCust_source(customerOld.getCust_source());
        customerListOld.setCust_industry(customerOld.getCust_industry());
        customerListOld.setCust_level(customerOld.getCust_level());
        //redis中的名称
        String redisCustomerName =  customer.getCust_source()+
                                    customer.getCust_industry()+
                                    customer.getCust_level();
        Customer customerList = new Customer();
        customerList.setCust_source(customer.getCust_source());
        customerList.setCust_industry(customer.getCust_industry());
        customerList.setCust_level(customer.getCust_level());
        //判断redis中有没有这个key
        Set<String> keys = redisTemplate.keys(redisCustomerName);
        if (!keys.isEmpty()){       //不为空，有相应的key，说明查询的次数较多，要及时的更新redis中的数据
            //创建人为当前用户
            SysUser sys_user = (SysUser) session.getAttribute("SYS_USER");
            customer.setCust_create_id(sys_user.getUser_id());
            //修改信息
            int i = customerService.updateCustomer(customer);
            if (i>0){
                System.out.println("更新成功");
                //将数据存在redis中
                redisTemplate.opsForValue().set(redisCustomerName,customerService.selectCustomerList(customerList));
            }

        }else{      //没有相应的key，说明该信息查询的次数较少，不需要把它放在redis中
            //创建人为当前用户
            SysUser sys_user = (SysUser) session.getAttribute("SYS_USER");
            customer.setCust_create_id(sys_user.getUser_id());
            int i = customerService.updateCustomer(customer);
            if (i>0){
                System.out.println("更新成功");
            }
        }


        //未修改前的数据
        String cust_source = customerOld.getCust_source();
        String cust_industry = customerOld.getCust_industry();
        String cust_level = customerOld.getCust_level();
        //redis中的名称
        String oldRedisCustomerName = cust_source+cust_industry+cust_level;
        Set<String> oldKeys = redisTemplate.keys(oldRedisCustomerName);
        System.out.println(oldKeys);
        if (!oldKeys.isEmpty()){       //不为空，有相应的key，说明查询的次数较多，要及时的更新redis中的数据
            System.out.println("key不为空,需要修改value的属性"+keys);
                //将数据存在redis中
                redisTemplate.opsForValue().set(oldRedisCustomerName,customerService.selectCustomerList(customerListOld));
        }else{      //没有相应的key，说明该信息查询的次数较少，不需要把它放在redis中
            System.out.println("啥也不用做");
        }
        return "customer/list";
    }
}
