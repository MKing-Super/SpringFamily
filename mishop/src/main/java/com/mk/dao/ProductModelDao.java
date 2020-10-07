package com.mk.dao;

import com.mk.po.ProductModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductModelDao {
    //查询各种商品的具体配置信息
    public List<ProductModel> findProductModel(@Param("product_id") Integer product_id);
}
