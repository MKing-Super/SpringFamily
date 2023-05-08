package pers.mk.opspace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import pers.mk.api.model.Son1;

import java.io.PrintStream;

/**
 * @author MK
 */
@Slf4j
@EnableAutoConfiguration
@ComponentScan
//@SpringBootApplication 可以用 @EnableAutoConfiguration 和 @ComponentScan 替代
public class OpenspaceApplication {
    private static final String[] BANNER = {
            "----------------------------------------",
            ">>>>>>>>>>>>>>>> BANNER >>>>>>>>>>>>>>>>",
            ">>>>>>>>>>>>>>>> BANNER >>>>>>>>>>>>>>>>",
            ">>>>>>>>>>>>>>>> BANNER >>>>>>>>>>>>>>>>",
            "----------------------------------------"};

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpenspaceApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        Banner banner = new Banner() {
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
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> OpenspaceApplication Started >>>>>>>>>>>>>>>>>>");
        String[] beanNamesForType = run.getBeanNamesForType(Son1.class);
        for (String str : beanNamesForType){
            System.out.println(str);
        }

    }
}
