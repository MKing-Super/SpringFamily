package com.mk;

import com.mk.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SpringbootBootcrmApplicationTests {
    @Autowired
    private SysUserService sysUserService;

    @Test
    void contextLoads() {

    }

}
