package com.mk.dao;

import com.mk.po.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    //查询商品信息
    public List<Product> findAllProducts(@Param("product_id") Integer product_id);
}
