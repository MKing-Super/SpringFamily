package pers.mk.interview.a;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Java11 {

    public static void main(String[] args) {
        String str = "Hello";
        Integer num = 100;

        // 获取对象的运行时类
        Class<?> strClass = str.getClass();
        Class<?> numClass = num.getClass();

        System.out.println("str 的类: " + strClass.getName());  // java.lang.String
        System.out.println("num 的类: " + numClass.getName());  // java.lang.Integer


        // 获取类的所有公共方法
        Method[] methods = strClass.getMethods();
        System.out.println("String 类的方法:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("--------------------------------------------------------------");

        Animal11 animal = new Dog11();
        // 判断运行时类型
        Class<?> runtimeClass = animal.getClass();
        System.out.println("实际类型: " + runtimeClass.getName()); // Dog

        // 检查是否是某个类的实例
        System.out.println("是 Dog11 吗? " + (runtimeClass == Dog11.class)); // true
        System.out.println("是 Animal11 吗? " + (runtimeClass == Animal11.class)); // false

        System.out.println("--------------------------------------------------------------");

        Object obj = "Hello";
        System.out.println(obj.getClass() == Object.class); // false
        System.out.println(obj.getClass() == String.class); // true
        System.out.println(obj instanceof Object); // true
        System.out.println(obj instanceof String); // true
        System.out.println(obj instanceof Serializable); // true
    }

}

class Animal11 { }
class Dog11 extends Animal11 { }
