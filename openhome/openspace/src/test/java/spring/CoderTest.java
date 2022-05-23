package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.mk.opspace.config.CoderConfig;
import pers.mk.opspace.service.Coder;
import pers.mk.opspace.service.impl.JavaCoder;

public class CoderTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoderConfig.class);
        JavaCoder coder = (JavaCoder) context.getBean("coder");
        coder.coding();
    }
}
