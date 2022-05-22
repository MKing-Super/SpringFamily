package pers.mk.opspace.service.impl;

import com.mk.data.mapper.DataMapper;
import org.springframework.stereotype.Service;
import pers.mk.opspace.service.DataService;

import javax.annotation.Resource;

@Service
public class DataServiceImpl implements DataService {

    @Resource
    DataMapper dataMapper;

    @Override
    public String selectName() {
        return dataMapper.selectName();
    }
}
