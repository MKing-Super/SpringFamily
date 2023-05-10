package pers.mk.opspace.spring.annotation.test7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * @describe: 注解信息的获取
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test7
 * @Date 2023/5/10 11:05
 * @Version 1.0
 */
public class UseAnnotation7<@Ann7("T0是在类上声明的一个泛型类型变量") T0, @Ann7("T1是在类上声明的一个泛型类型变量") T1> {

    public <@Ann7("T2是在类上声明的一个泛型类型变量") T2> void m1() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        // 获取类上的注解信息
        System.out.println("获取类上的注解信息：");
        TypeVariable<Class<UseAnnotation7>>[] typeParameters = UseAnnotation7.class.getTypeParameters();
        for (TypeVariable<Class<UseAnnotation7>> ty : typeParameters) {
            System.out.println(ty.getName());
            for (Annotation av : ty.getAnnotations()) {
                System.out.println(av);
            }
        }

        System.out.println("--------------------------------");

        System.out.println("获取方法上的注解信息：");
        TypeVariable<Method>[] m1s = UseAnnotation7.class.getDeclaredMethod("m1").getTypeParameters();
        for (TypeVariable<Method> ty : m1s) {
            System.out.println(ty.getName());
            for (Annotation av : ty.getAnnotations()) {
                System.out.println(av);
            }
        }
    }


}
