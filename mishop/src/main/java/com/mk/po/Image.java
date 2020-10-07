package com.mk.po;

public class Image {
    private int image_id;
    private int product_id;
    private String image_url;
    private int image_type;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getImage_type() {
        return image_type;
    }

    public void setImage_type(int image_type) {
        this.image_type = image_type;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image_id=" + image_id +
                ", product_id=" + product_id +
                ", image_url='" + image_url + '\'' +
                ", image_type=" + image_type +
                '}';
    }
}
