package pers.mk.opspace.spring.annotation.test12;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test12
 * @Date 2023/5/10 14:05
 * @Version 1.0
 */
@Ann12(name = "我炸了")
@Ann12(name = "wozhale")
public class UseAnnotation12 {

    @Ann12s({@Ann12(name = "上天了"),@Ann12(name = "shangtianle")})
    private String v1;


    public static void main(String[] args) throws NoSuchFieldException {
        Annotation[] annotations = UseAnnotation12.class.getAnnotations();
        for (Annotation an : annotations){
            System.out.println(an);
        }

        System.out.println("-------------------------");

        Field v1 = UseAnnotation12.class.getDeclaredField("v1");
        Annotation[] declaredAnnotations = v1.getDeclaredAnnotations();
        for (Annotation an : declaredAnnotations){
            System.out.println(an);
        }
    }

}
