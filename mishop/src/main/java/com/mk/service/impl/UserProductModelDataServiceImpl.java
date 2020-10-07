package com.mk.service.impl;

import com.mk.dao.UserProductModelDataDao;
import com.mk.po.UserProductModelData;
import com.mk.service.UserProductModelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductModelDataServiceImpl  implements UserProductModelDataService {
    @Autowired
    private UserProductModelDataDao userProductModelDataDao;
    //向t_user_product_model_data表添加所选商品的详细信息
    @Override
    public int addUserProductModelData(UserProductModelData userProductModelData) {
        return userProductModelDataDao.addUserProductModelData(userProductModelData);
    }
    //删除信息
    @Override
    public int deleteUserProductModelData(Integer productid) {
        return userProductModelDataDao.deleteUserProductModelData(productid);
    }
}
