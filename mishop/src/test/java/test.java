import com.mk.po.*;
import com.mk.service.*;
import com.mk.util.createUUID;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;
import java.util.List;
import java.util.SortedMap;

public class test {
    @Test
    public void register(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        User user = new User();
        user.setUserid("4");
        user.setUsername("0");
        user.setMobile_phone("12345678900");
        user.setPassword("0");
        int register = bean.register(user);
        System.out.println(register);
    }

    @Test
    public void login(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        User login = bean.login("123456", "123");
        System.out.println(login);
    }

    @Test
    public void alterUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        User user  = new User();
        user.setMobile_phone("123456");
        user.setUsername("makun");
        user.setPassword("123");
        int i = bean.alterUser(user);
        System.out.println(i);
    }

    @Test
    public void findShoppingCartById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ShoppingCartService bean = applicationContext.getBean(ShoppingCartService.class);
        List<ShoppingCart> shoppingCartById = bean.findShoppingCartById("1");
        for (ShoppingCart shoppingCart : shoppingCartById){
            System.out.println(shoppingCart);
        }
    }


    @Test
    public void findAllProducts(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService bean = applicationContext.getBean(ProductService.class);
        List<Product> allProducts = bean.findAllProducts(1);
        for (Product product : allProducts){
            System.out.println(product);
        }

    }

    @Test
    public void findProductModel(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductModelService bean = applicationContext.getBean(ProductModelService.class);
        List<ProductModel> productModels = bean.findProductModel(2);
        for (ProductModel productModel : productModels){
            System.out.println(productModel);
        }
    }

    @Test
    public void addShoppingCart(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ShoppingCartService bean = applicationContext.getBean(ShoppingCartService.class);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserid("1");
        shoppingCart.setProduct_id(2);
        shoppingCart.setTotalnumber(1);
        shoppingCart.setTotalprice(10000);
        int i = bean.addShoppingCart(shoppingCart);
        System.out.println(i);
    }

    @Test
    public void addUserProductModelData(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserProductModelDataService bean = applicationContext.getBean(UserProductModelDataService.class);
        UserProductModelData userProductModelData = new UserProductModelData();
        userProductModelData.setUserid("1");
        userProductModelData.setProductid(1);
        userProductModelData.setModelid(1);
        userProductModelData.setModeldataid(1);

        bean.addUserProductModelData(userProductModelData);
    }

    @Test
    public void findUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        User user = bean.findUser("1");
        System.out.println(user);

    }
}
