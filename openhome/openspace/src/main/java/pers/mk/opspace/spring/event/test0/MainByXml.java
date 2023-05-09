package pers.mk.opspace.spring.event.test0;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @describe: 根据xml文件扫面当前包及以下的包
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test0
 * @Date 2023/5/9 10:28
 * @Version 1.0
 */
public class MainByXml {
    public static void main(String[] args) {
        String path = "classpath:/config/spring-event-test0.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(path);
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        userRegisterService.registerUser("mk");
    }
}
