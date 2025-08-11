package per.mk.pirate.frame;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 注解标记 该方法通过 url 调用
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface PirateUrl {

    // url 路径
    String[] value() default {};

}
