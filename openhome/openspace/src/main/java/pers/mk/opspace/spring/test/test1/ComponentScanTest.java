package pers.mk.opspace.spring.test.test1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.test
 * @Date 2023/5/15 11:03
 * @Version 1.0
 */
public class ComponentScanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(UserModel1.class);
        for (String beanName : context.getBeanDefinitionNames()){
            System.out.println(beanName+"::"+context.getBean(beanName));
        }

    }
}
