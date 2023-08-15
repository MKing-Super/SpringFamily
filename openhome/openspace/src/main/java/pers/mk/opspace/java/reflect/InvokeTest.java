package pers.mk.opspace.java.reflect;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.reflect
 * @Date 2023/8/15 11:21
 * @Version 1.0
 */
public class InvokeTest implements InvokeTestInterface {

    @Override
    public void test0() {
        System.out.println("==== this is test0");
    }

    @Override
    public String test1() {
        System.out.println("==== this is test1");
        return "i am test1";
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("pers.mk.opspace.java.reflect.InvokeTestInterface");
        Method[] methods = aClass.getMethods();
        InvokeTestInterface invokeTestInterface = new InvokeTest();
        for (Method m : methods){
            Object invokeResult = m.invoke(invokeTestInterface);
            System.out.println(invokeResult);
        }


    }




}
