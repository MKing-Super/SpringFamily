package pers.mk.tools.converter;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import pers.mk.tools.converter.event.EventListener;
import pers.mk.tools.converter.event.EventMulticaster;
import pers.mk.tools.converter.listener.PushEventListener;
import pers.mk.tools.converter.service.AccountService;
import pers.mk.tools.converter.service.impl.YourAccountService;

@SpringBootApplication
@MapperScan(basePackages = "pers.mk.tools.converter.mapper")
public class ToolsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolsApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(ToolsApplication.class, args);
        ConfigurableApplicationContext run = new SpringApplicationBuilder()
                .sources(ToolsApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        LOGGER.warn(">>>>>>>>>>>>>>>>>>>>> ToolsApplication started~ >>>>>>>>>>>>>>>>>>>>>>");
        String[] beanNamesForAnnotation = run.getBeanNamesForType(AccountService.class);
        for (String str : beanNamesForAnnotation) {
            System.out.println(str);
        }
        ObjectProvider<YourAccountService> beanProvider = run.getBeanProvider(YourAccountService.class);
        String method = beanProvider.getIfAvailable().getMethod();
        System.out.println(method);
    }

}
