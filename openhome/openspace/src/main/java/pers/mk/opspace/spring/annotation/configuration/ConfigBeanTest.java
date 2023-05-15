package pers.mk.opspace.spring.annotation.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.configuration
 * @Date 2023/5/11 9:42
 * @Version 1.0
 */
public class ConfigBeanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);
        System.out.println("-------------------------------------");
        for (String beanName : context.getBeanDefinitionNames()){
            String[] aliases = context.getAliases(beanName);
            System.out.println(beanName + "：：别名：：" + Arrays.toString(aliases));
        }
    }
}
