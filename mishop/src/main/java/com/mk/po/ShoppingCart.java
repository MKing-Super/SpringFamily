package com.mk.po;
//tb_person                 Person
public class ShoppingCart {
    private int shoppingcart_id;
    private String userid;
    private int product_id;
    private int totalnumber;
    private double totalprice;
    private int gift_id;
    private Product product;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getShoppingcart_id() {
        return shoppingcart_id;
    }

    public void setShoppingcart_id(int shoppingcart_id) {
        this.shoppingcart_id = shoppingcart_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(int totalnumber) {
        this.totalnumber = totalnumber;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingcart_id=" + shoppingcart_id +
                ", userid='" + userid + '\'' +
                ", product_id=" + product_id +
                ", totalnumber=" + totalnumber +
                ", totalprice=" + totalprice +
                ", gift_id=" + gift_id +
                ", product=" + product +
                '}';
    }
}
