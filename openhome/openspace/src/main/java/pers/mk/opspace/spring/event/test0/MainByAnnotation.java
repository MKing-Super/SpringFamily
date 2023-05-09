package pers.mk.opspace.spring.event.test0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @describe: 通过注解扫描当前包及以下的包
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test0
 * @Date 2023/5/9 10:19
 * @Version 1.0
 */
public class MainByAnnotation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        userRegisterService.registerUser("mk");
    }

}
