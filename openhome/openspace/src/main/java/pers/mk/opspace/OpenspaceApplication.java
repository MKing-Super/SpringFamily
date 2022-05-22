package pers.mk.opspace;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.*;
import org.springframework.beans.*;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * @author MK
 */
@Slf4j
@SpringBootApplication
public class OpenspaceApplication {
    private static final String[] BANNER = {
            "----------------------------------------" ,
            ">>>>>>>>>>>>>>>> BANNER >>>>>>>>>>>>>>>>" ,
            ">>>>>>>>>>>>>>>> BANNER >>>>>>>>>>>>>>>>" ,
            ">>>>>>>>>>>>>>>> BANNER >>>>>>>>>>>>>>>>" ,
            "----------------------------------------"};

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpenspaceApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        Banner banner = new Banner(){
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                for (String line : BANNER) {
                    out.println(line);
                }
                out.println();
            }
        };
        app.setBanner(banner);
        ConfigurableApplicationContext run = app.run();
        log.info(">>>>>>>>>>>>>>>> OpenspaceApplication Started >>>>>>>>>>>>>>>>>>");
        Object dataconfig = run.getBean("mkSqlSessionFactory");
//        System.out.println(dataconfig);

    }
}
