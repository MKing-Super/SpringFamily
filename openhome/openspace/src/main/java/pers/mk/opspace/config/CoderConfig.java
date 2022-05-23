package pers.mk.opspace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.mk.opspace.service.Coder;
import pers.mk.opspace.service.impl.JavaCoder;

@Configuration
public class CoderConfig {

    @Bean
    public Coder coder(){
        return new JavaCoder();
    }

}
