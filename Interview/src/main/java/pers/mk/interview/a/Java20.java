package pers.mk.interview.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Java20 {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("java.util.ArrayList");
        Method[] methods = aClass.getMethods();
        Constructor<?> constructor = aClass.getConstructor(aClass.getClass());
        Object hello = constructor.newInstance();
        System.out.println(hello);
    }
}
