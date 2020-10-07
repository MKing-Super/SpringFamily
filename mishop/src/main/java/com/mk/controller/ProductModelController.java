package com.mk.controller;

import com.mk.po.ProductModel;
import com.mk.service.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 查询商品的详细信息、
*
* */
@Controller
@RequestMapping(value = "/productmodel")
public class ProductModelController {
    @Autowired
    private ProductModelService productModelService;
    //查询商品类型的详细信息
    @RequestMapping(value = "/productmodel",method = RequestMethod.POST)
    @ResponseBody
    public List<ProductModel> findProductModel(int product_id){
        List<ProductModel> productModels = productModelService.findProductModel(product_id);
        for (ProductModel productModel : productModels){
            System.out.println(productModel);
        }
        return productModels;
    }



}
