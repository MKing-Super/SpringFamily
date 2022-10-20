package pers.mk.opspace.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义日志注解（切面环绕通知）
 * @Author: kun.ma
 * @Date: 2022/10/9 13:36
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLogAnnotation {

    String ope() default "";

    String parameter() default "";

}
