package pers.mk.tools.converter;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "pers.mk.tools.converter.mapper")
public class ToolsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ToolsApplication.class, args);
        LOGGER.warn(">>>>>>>>>>>>>>>>>>>>> ToolsApplication started~ >>>>>>>>>>>>>>>>>>>>>>");
    }

}
