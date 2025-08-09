package per.mk.pirate.frame;

import per.mk.pirate.frame.test.TestController;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BeanRegister {

    public static Map<String,Object> beanRegisterMap;

    public static Map<String,Object> start(){
        try {
            beanRegisterMap = new ConcurrentHashMap<>();
            Class<?> aClass = TestController.class;
            String name = aClass.getName();
            Object testController = aClass.newInstance();
            beanRegisterMap.put(name,testController);
            System.out.println(beanRegisterMap);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return beanRegisterMap;
        }

    }

}
