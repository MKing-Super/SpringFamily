package pers.mk.opspace.spring.annotation.test7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation
 * @Date 2023/5/10 11:02
 * @Version 1.0
 */
@Target(value = {ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann7 {
    String value();
}
