package com.mkinfo.carhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mkinfo.carhouse.dao")
public class CarhouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarhouseApplication.class, args);
    }

}
