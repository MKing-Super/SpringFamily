package com.mk.po;

import java.util.List;
//配置的类型
public class ProductModel {
    private int model_id;
    private String model_name;
    private int product_id;
    private List<ModelData> modelData;

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public List<ModelData> getModelData() {
        return modelData;
    }

    public void setModelData(List<ModelData> modelData) {
        this.modelData = modelData;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "model_id=" + model_id +
                ", model_name='" + model_name + '\'' +
                ", product_id=" + product_id +
                ", modelData=" + modelData +
                '}';
    }
}
