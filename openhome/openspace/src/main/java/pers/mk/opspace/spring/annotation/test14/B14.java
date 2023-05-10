package pers.mk.opspace.spring.annotation.test14;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test14
 * @Date 2023/5/10 14:27
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@A14
public @interface B14 {

    String value() default "b";

    @AliasFor(annotation = A14.class,value = "value")
    String a14Value();

}
