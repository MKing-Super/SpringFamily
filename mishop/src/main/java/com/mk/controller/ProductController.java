package com.mk.controller;

import com.aliyuncs.http.HttpRequest;
import com.aliyuncs.http.HttpResponse;
import com.mk.po.Product;
import com.mk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    //显示所有商品的信息
    @RequestMapping(value = "/showallproducts",method = RequestMethod.POST)
    @ResponseBody
    public List<Product> showallproducts(){
        List<Product> allProducts = productService.findAllProducts(null);
        return allProducts;
    }
    //显示一件商品的信息
    @RequestMapping(value = "/findproduct",method = RequestMethod.POST)
    @ResponseBody
    public List<Product> showproduct(Integer product_id){
        List<Product> products = productService.findAllProducts(product_id);
        System.out.println(products.get(0).getPrice());
        return products;
    }

    //去购买商品
    @RequestMapping(value = "/tobuy/{id}",method = RequestMethod.GET)
    public String tobuy(@PathVariable("id") int id,HttpSession session){
        session.setAttribute("BUY_PRODUCT_ID",id);
        return "main/buy";
    }


}
