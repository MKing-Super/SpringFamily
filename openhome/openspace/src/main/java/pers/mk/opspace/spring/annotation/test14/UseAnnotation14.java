package pers.mk.opspace.spring.annotation.test14;

import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test14
 * @Date 2023/5/10 14:29
 * @Version 1.0
 */
@B14(value = "我炸了",a14Value = "wozhale")
public class UseAnnotation14 {
    public static void main(String[] args) {
        B14 b14 = AnnotatedElementUtils.getMergedAnnotation(UseAnnotation14.class, B14.class);
        System.out.println(b14);
        A14 a14 = AnnotatedElementUtils.getMergedAnnotation(UseAnnotation14.class, A14.class);
        System.out.println(a14);
    }
}
