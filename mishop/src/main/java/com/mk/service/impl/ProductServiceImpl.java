package com.mk.service.impl;

import com.mk.dao.ProductDao;
import com.mk.po.Product;
import com.mk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAllProducts(Integer product_id) {
        return productDao.findAllProducts(product_id);
    }
}
