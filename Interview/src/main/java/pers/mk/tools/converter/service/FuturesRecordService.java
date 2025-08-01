package pers.mk.tools.converter.service;

import org.springframework.stereotype.Service;
import pers.mk.tools.converter.model.FuturesRecord;


public interface FuturesRecordService {

    FuturesRecord selectById(Integer id);

}
