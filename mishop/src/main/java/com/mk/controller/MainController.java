package com.mk.controller;

import com.mk.po.Product;
import javafx.geometry.Pos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/main")
public class MainController {
    //去主页面
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String tomain(){
        return "main/main";
    }
    //去查看“购物车”
    @RequestMapping(value = "/shoppingCart",method = RequestMethod.GET)
    public String shoppingCart(){
        return "shoppingcart/shoppingCart";
    }
    //去查看“我的”
    @RequestMapping(value = "/mine",method = RequestMethod.GET)
    public String mine(){
        return "main/mine";
    }










}
