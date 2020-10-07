package com.mk.service.impl;

import com.mk.dao.ProductModelDao;
import com.mk.po.ProductModel;
import com.mk.service.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productModelService")
@Transactional
public class ProductModelServiceImpl implements ProductModelService {
    @Autowired
    private ProductModelDao productModelDao;

    @Override
    public List<ProductModel> findProductModel(Integer product_id) {
        return productModelDao.findProductModel(product_id);
    }
}
