package pers.mk.opspace.annotation;

import pers.mk.api.enums.DemoEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MK
 */
/*
 * 这是一个自定义的注解(Annotation)类 在定义注解(Annotation)类时使用了另一个注解类Retention。
 * 在注解类上使用另一个注解类，那么被使用的注解类就称为元注解。
 *
 * 这里是在注解类MyAnnotation上使用另一个注解类，这里的Retention称为元注解。
 *
 *
 * @Retention(RetentionPolicy.SOURCE)
 * 这个注解的意思是让MyAnnotation注解只在java源文件中存在，编译成.class文件后注解就不存在了。
 * @Retention(RetentionPolicy.CLASS)
 * 这个注解的意思是让MyAnnotation注解在java源文件(.java文件)中存在，编译成.class文件后注解也还存在，
 * 被MyAnnotation注解类标识的类被类加载器加载到内存中后MyAnnotation注解就不存在了。
 * @Retention(RetentionPolicy.RUNTIME)
 * 注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在。
 *
 *
 * @Target 注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分。
 * ElementType.TYPE：能修饰类、接口或枚举类型
 * ElementType.FIELD：能修饰成员变量
 * ElementType.METHOD：能修饰方法
 * ElementType.PARAMETER：能修饰参数
 * ElementType.CONSTRUCTOR：能修饰构造器
 * ElementType.LOCAL_VARIABLE：能修饰局部变量
 * ElementType.ANNOTATION_TYPE：能修饰注解
 * ElementType.PACKAGE：能修饰包
 *
 *
 * @Inherited 表示该注解具有继承性。
 * 如果一个使用了@Inherited 修饰的annotation 类型被用于一个class，则这个annotation 将被用于该class 的子类。
 *
 *
 * @Documented 在用javadoc命令生成API文档后，文档里会出现该注解说明。
 *
 *
 * @Repeatable 是JDK1.8新加入的，它表示在同一个位置重复相同的注解，
 * 在没有该注解前，一般是无法在同一个类型上使用相同的注解的。
 *
 * */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Test {

    String color() default "这里是color的默认值";

    String value() default "这里是value的默认值";

    int[] array() default {1,2,3};

    DemoEnum lamp() default DemoEnum.LARGE;

    MetaAnnotation annotationAttr() default @MetaAnnotation("这里是annotationAttr的默认值");

}
