package com.mk.service;

import com.mk.po.Product;

import java.util.List;

public interface ProductService {
    //查询商品信息
    public List<Product> findAllProducts(Integer product_id);

}
