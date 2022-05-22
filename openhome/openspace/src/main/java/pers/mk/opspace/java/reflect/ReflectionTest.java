package pers.mk.opspace.java.reflect;

import pers.mk.opspace.annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {


    public static void main(String[] args) {
        try {

            /*
            Modifier用于判断一个类中的基本属性。如：是否是public
             */
            String name = "java.lang.Double";
            Class<?> cl = Class.forName(name);
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0){
                System.out.print(modifiers + " | ");
                System.out.print(Modifier.isPublic(cl.getModifiers()) + " | ");
            }
            System.out.println("class " + name);
            /*
            获取父类的class
             */
            Class<?> supercl = cl.getSuperclass();
            if (supercl != null && supercl != Object.class){
                System.out.println("extends " + supercl.getName());
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>> constructor 构造");
            Constructor<?>[] constructors = cl.getDeclaredConstructors();
            for (Constructor c : constructors){
                String cname = c.getName();
                String cmodifiers = Modifier.toString(c.getModifiers());
                if (cmodifiers.length() > 0){
                    System.out.print(cmodifiers + " | ");
                }
                Class[] parameterTypes = c.getParameterTypes();
                for (int j = 0 ; j < parameterTypes.length ; j++){
                    System.out.println(parameterTypes[j].getName());
                }
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> methods 方法");
            Method[] methods = cl.getDeclaredMethods();
            for (Method m : methods){
                Class<?> returnType = m.getReturnType();
                String mname = m.getName();
                String s = Modifier.toString(m.getModifiers());
                if (modifiers.length() > 0){
                    //基本属性
                    System.out.print(modifiers + " | ");
                }
                //返回值
                System.out.print(returnType.getName() + " | ");
                //方法名
                System.out.print(mname + " | ");
                Class<?>[] parameterTypes = m.getParameterTypes();
                for (int j = 0 ; j < parameterTypes.length ; j++){
                    if (j > 0){
                        //入参
                        System.out.print(parameterTypes[j].getName() == null ? "null":parameterTypes[j].getName());
                    }
                }
                System.out.println();
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> fields 域");
            Field[] declaredFields = cl.getDeclaredFields();
            for (Field f : declaredFields){
                Class<?> type = f.getType();
                String fname = f.getName();
                String s = Modifier.toString(f.getModifiers());
                if (modifiers.length() > 0){
                    System.out.print(modifiers + " | ");
                }
                System.out.println(type.getName() + " | " + fname);
            }
            System.out.println("检查自定义注解~");
            System.out.println(AnnotationUse.class.isAnnotationPresent(Test.class));
            System.out.println(AnnotationUse.class.getAnnotation(Test.class));
            System.out.println(AnnotationUse.class.getAnnotation(Test.class).lamp().getType());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
