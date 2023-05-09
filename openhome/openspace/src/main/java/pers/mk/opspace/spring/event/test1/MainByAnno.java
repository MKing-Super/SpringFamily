package pers.mk.opspace.spring.event.test1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test1
 * @Date 2023/5/9 10:50
 * @Version 1.0
 */
public class MainByAnno {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        UserRegisterService bean = context.getBean(UserRegisterService.class);
        bean.reg("mk111");
    }
}
