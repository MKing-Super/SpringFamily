package com.mk.data.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataMapper {

    String selectName();

}
