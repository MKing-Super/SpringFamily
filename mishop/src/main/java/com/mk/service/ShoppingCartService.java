package com.mk.service;


import com.mk.po.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    //查询购物车
    public List<ShoppingCart> findShoppingCartById(String userid);
    //删除购物车信息
    public int deleteShoppingCartById(Integer shoppingcart_id);
    //添加购物车信息
    public int addShoppingCart(ShoppingCart shoppingCart);
}
