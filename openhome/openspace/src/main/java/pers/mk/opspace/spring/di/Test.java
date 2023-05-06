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
        DemoModel deomModel = context.getBean("deomModel", DemoModel.class);
        System.out.println(JSON.toJSONString(deomModel));
    }

}
