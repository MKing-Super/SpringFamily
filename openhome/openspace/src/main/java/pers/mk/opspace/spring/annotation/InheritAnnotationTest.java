package pers.mk.opspace.spring.annotation;

import java.lang.annotation.*;

/**
 * @describe: 从输出中可以看出类可以继承父类上被@Inherited修饰的注解，而不能继承接口上被@Inherited修饰的注解，这个一定要注意
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation
 * @Date 2023/5/10 13:29
 * @Version 1.0
 */
public class InheritAnnotationTest {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface A1{

    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface A2{

    }

    @A1
    interface I1{

    }

    @A2
    static class C1{

    }

    static class C2 extends C1 implements I1{

    }

    public static void main(String[] args) {
        Annotation[] annotations = C2.class.getAnnotations();
        for (Annotation an : annotations){
            System.out.println(an);
        }
    }

}
