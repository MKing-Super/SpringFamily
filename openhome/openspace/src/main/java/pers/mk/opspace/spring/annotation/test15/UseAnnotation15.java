package pers.mk.opspace.spring.annotation.test15;

import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test15
 * @Date 2023/5/10 14:41
 * @Version 1.0
 */
@A15(v1 = "我炸了")
public class UseAnnotation15 {

    @A15(v2 = "wozhale")
    private String name;

    public static void main(String[] args) throws NoSuchFieldException {
        for (Annotation an : UseAnnotation15.class.getAnnotations()){
            System.out.println(an);
        }
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseAnnotation15.class, A15.class));

        System.out.println("-----------------------------------------");

        for (Annotation an : UseAnnotation15.class.getDeclaredField("name").getAnnotations()){
            System.out.println(an);
        }
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseAnnotation15.class.getDeclaredField("name"), A15.class));

    }

}
