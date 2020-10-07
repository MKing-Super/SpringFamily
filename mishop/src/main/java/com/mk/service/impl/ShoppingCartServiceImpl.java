package com.mk.service.impl;

import com.mk.dao.ShoppingCartDao;
import com.mk.po.ShoppingCart;
import com.mk.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("shoppingCartService")
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    //通过用户ID查询购物车商品信息
    @Override
    public List<ShoppingCart> findShoppingCartById(String userid) {
        return shoppingCartDao.findShoppingCartById(userid);
    }
    //删除购物车信息
    @Override
    public int deleteShoppingCartById(Integer shoppingcart_id) {
        return shoppingCartDao.deleteShoppingCartById(shoppingcart_id);
    }
    //添加购物车信息
    @Override
    public int addShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartDao.addShoppingCart(shoppingCart);
    }
}
