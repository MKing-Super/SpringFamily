package pers.mk.opspace.spring.annotation.test15;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test15
 * @Date 2023/5/10 14:39
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface A15 {

    @AliasFor(value = "v2")
    String v1() default "";

    @AliasFor(value = "v1")
    String v2() default "";

}
