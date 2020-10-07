package com.mk.dao;

import com.mk.po.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//tb_person     Person     PersonMapper
public interface ShoppingCartDao {
    //通过用户ID查询购物车商品信息
    public List<ShoppingCart> findShoppingCartById(@Param("userid") String userid);
    //通过shoppingcart_id删除购物车信息
    public int deleteShoppingCartById(@Param("shoppingcart_id") Integer shoppingcart_id);
    //添加购物车信息
    public int addShoppingCart(ShoppingCart shoppingCart);
}
