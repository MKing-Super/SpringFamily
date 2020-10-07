package com.mk.po;

public class UserProductModelData {
    private int id;
    private String userid;
    private int productid;
    private int modelid;
    private int modeldataid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getModelid() {
        return modelid;
    }

    public void setModelid(int modelid) {
        this.modelid = modelid;
    }

    public int getModeldataid() {
        return modeldataid;
    }

    public void setModeldataid(int modeldataid) {
        this.modeldataid = modeldataid;
    }

    @Override
    public String toString() {
        return "UserProductModelData{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", productid=" + productid +
                ", modelid=" + modelid +
                ", modeldataid=" + modeldataid +
                '}';
    }
}
