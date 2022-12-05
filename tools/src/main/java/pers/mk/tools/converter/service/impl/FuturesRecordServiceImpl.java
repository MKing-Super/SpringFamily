package pers.mk.tools.converter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mk.tools.converter.mapper.FuturesRecordMapper;
import pers.mk.tools.converter.model.FuturesRecord;
import pers.mk.tools.converter.service.FuturesRecordService;

import javax.annotation.Resource;
@Service
public class FuturesRecordServiceImpl implements FuturesRecordService {
    @Autowired
    private FuturesRecordMapper futuresRecordMapper;

    @Override
    public FuturesRecord selectById(Integer id) {
        return futuresRecordMapper.selectById(id);
    }
}
