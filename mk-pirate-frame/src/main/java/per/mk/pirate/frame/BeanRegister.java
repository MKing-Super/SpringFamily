package per.mk.pirate.frame;

import per.mk.pirate.frame.test.TestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BeanRegister {

    public static Map<String,Object> beanRegisterMap;

    public static Map<String,Object> start(){
        try {
            Class<?> tClass = Class.forName("per.mk.pirate.frame.test.TestController");
            Constructor<?> constructor = tClass.getConstructor();
            Object o = constructor.newInstance();

            Method[] methods = o.getClass().getMethods();
            for (int i = 0 ; i < methods.length ; i++){
                if ("setName".equals(methods[i].getName())){
                    System.out.println(methods[i].getName());
                    Parameter[] parameters = methods[i].getParameters();
                    Class<?>[] types = new Class[parameters.length];
                    for (int j = 0 ; j < parameters.length ; j++) {
                        types[j] = parameters[j].getType();
                        System.out.println("参数类型: " + parameters[j].getType().getName());
                        System.out.println("参数名称: " + parameters[j].getName());
                    }
                    if (parameters.length == 2){
                        Object[] args = {"666",4444};
                        Method pmethod = o.getClass().getMethod("setName",types);
                        pmethod.invoke(o,args);
                    }

                }
            }


            beanRegisterMap = new ConcurrentHashMap<>();
            beanRegisterMap.put(tClass.getName(),o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return beanRegisterMap;
        }

    }

}
