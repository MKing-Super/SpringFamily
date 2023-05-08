package pers.mk.opspace.spring.di;

import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.mk.api.model.DemoModel;
import pers.mk.api.model.Father;
import pers.mk.api.model.Son1;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.di
 * @Date 2023/5/6 16:26
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        String path = "classpath:/config/learn-beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(path);
        Object di1 = context.getBean("di1");
        Object di2 = context.getBean("di2");
        Object di3 = context.getBean("di3");
        Object di4 = context.getBean("di4");
        System.out.println(JSON.toJSONString(di1));
        System.out.println(JSON.toJSONString(di2));
        System.out.println(JSON.toJSONString(di3));
        System.out.println(JSON.toJSONString(di4));

        String[] beanNamesForType = context.getBeanNamesForType(Son1.class);
        for (String str : beanNamesForType){
            Son1Listener bean = context.getBean(str, Son1Listener.class);
            bean.onEvent(new Son1(null,"mk"));
        }

    }

}
