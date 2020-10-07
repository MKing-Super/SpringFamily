package com.mk.controller;

import com.mk.po.ShoppingCart;
import com.mk.po.User;
import com.mk.po.UserProductModelData;
import com.mk.po.UserProductModelDataVO;
import com.mk.service.ShoppingCartService;
import com.mk.service.UserProductModelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserProductModelDataService userProductModelDataService;
    //查询购物车信息
    @RequestMapping(value = "/showproducts",method = RequestMethod.POST)
    @ResponseBody
    public List<ShoppingCart> showproducts(HttpSession session){
        User user = (User) session.getAttribute("USER_SESSION");
        List<ShoppingCart> shoppingCarts = shoppingCartService.findShoppingCartById(user.getUserid());
        for(int i=0;i<shoppingCarts.size();i++){
            System.out.println(shoppingCarts.get(i));
        }
        return  shoppingCarts;
    }
    //删除购物车信息
    @RequestMapping(value = "/deletemsg",method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteShoppingCartById(int shoppingcart_id,int productid){
        System.out.println(shoppingcart_id);
        System.out.println(productid);
        int i = shoppingCartService.deleteShoppingCartById(shoppingcart_id);
        int i1 = userProductModelDataService.deleteUserProductModelData(productid);
        if (i1>0){
            return true;
        }else {
            return false;
        }
    }

    //将商品加入购物车
    @RequestMapping(value = "/addshoppingcart",method = RequestMethod.GET)
    public String addshoppingcart(UserProductModelDataVO userList,double price,int totalnumber,HttpSession session){
        //得到t_user_product_model_data表的详细信息
        List<UserProductModelData> datas = userList.getUserProductModelDatas();
        //设置购物车的信息
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserid(datas.get(0).getUserid());       //登陆用户的ID
        shoppingCart.setProduct_id(datas.get(0).getProductid());//商品ID
        shoppingCart.setTotalnumber(totalnumber);               //购买总数
        double totalprice = totalnumber*price;
        shoppingCart.setTotalprice(totalprice);                 //总价格
        System.out.println();
        System.out.println(shoppingCart);
        System.out.println(datas);
        //添加一条购物车信息
        shoppingCartService.addShoppingCart(shoppingCart);
        //向t_user_product_model_data表添加所选商品的详细信息
        for (UserProductModelData u : datas){
            userProductModelDataService.addUserProductModelData(u);
        }
        return "main/main";
    }

}
