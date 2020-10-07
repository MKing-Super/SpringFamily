package com.mk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
    //去customer页面
    @GetMapping(value = "/tocusotmer")
    public String tocusotmer(){
        return "customer";
    }
    //去list页面
    @GetMapping(value = "/tolist")
    public String tolist(){
        return "customer/list";
    }
}
