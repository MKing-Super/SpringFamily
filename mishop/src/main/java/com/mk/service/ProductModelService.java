package com.mk.service;

import com.mk.po.ProductModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductModelService {
    //查询各种商品的具体配置信息
    public List<ProductModel> findProductModel(Integer product_id);
}
