package pers.mk.tools.converter;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(basePackages = "pers.mk.tools.converter.mapper")
public class ToolsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolsApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(ToolsApplication.class, args);
        new SpringApplicationBuilder()
                .sources(ToolsApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        LOGGER.warn(">>>>>>>>>>>>>>>>>>>>> ToolsApplication started~ >>>>>>>>>>>>>>>>>>>>>>");
    }

}
