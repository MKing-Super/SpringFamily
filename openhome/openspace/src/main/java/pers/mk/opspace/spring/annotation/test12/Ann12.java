package pers.mk.opspace.spring.annotation.test12;

import java.lang.annotation.*;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test12
 * @Date 2023/5/10 13:59
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Repeatable(Ann12s.class)
public @interface Ann12 {
    String name();
}
