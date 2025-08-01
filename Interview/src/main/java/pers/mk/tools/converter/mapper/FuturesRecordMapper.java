package pers.mk.tools.converter.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.mk.tools.converter.model.FuturesRecord;

public interface FuturesRecordMapper {

    FuturesRecord selectById(Integer id);

}
