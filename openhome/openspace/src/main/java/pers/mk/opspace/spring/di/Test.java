package pers.mk.opspace.spring.di;

import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.mk.api.model.DemoModel;

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
    }

}
