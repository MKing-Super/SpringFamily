package pers.mk.opspace.spring.test.test2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.test.test2
 * @Date 2023/5/15 11:25
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean.class);
        for (String beanName : context.getBeanDefinitionNames()){
            System.out.println(beanName + "::" + context.getBean(beanName));
        }
    }
}
