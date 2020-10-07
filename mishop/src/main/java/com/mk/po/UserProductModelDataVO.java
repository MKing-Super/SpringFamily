package com.mk.po;

import java.util.List;

public class UserProductModelDataVO {
    private List<UserProductModelData> userProductModelDatas;

    public List<UserProductModelData> getUserProductModelDatas() {
        return userProductModelDatas;
    }

    public void setUserProductModelDatas(List<UserProductModelData> userProductModelDatas) {
        this.userProductModelDatas = userProductModelDatas;
    }

    @Override
    public String toString() {
        return "UserProductModelDataVO{" +
                "userProductModelDatas=" + userProductModelDatas +
                '}';
    }
}
