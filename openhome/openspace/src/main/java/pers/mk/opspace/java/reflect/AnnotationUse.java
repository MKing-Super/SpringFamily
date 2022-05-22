package pers.mk.opspace.java.reflect;

import pers.mk.opspace.annotation.MetaAnnotation;
import pers.mk.opspace.annotation.Test;
import pers.mk.opspace.enums.DemoEnum;

import java.lang.reflect.Method;

@Test(
        color = "red",
        value = "哈哈",
        array = {22,33},
        lamp = DemoEnum.MEDIUM,
        annotationAttr = @MetaAnnotation("这里是AnnotationUse类上的注解")
)
public class AnnotationUse {

    @Test("这里是main方法的注解")
    public static void main(String[] args) {
        Class<AnnotationUse> cl = AnnotationUse.class;
        System.out.println(">>>>>>>> main方法上的注解");
        Method[] declaredMethods = cl.getDeclaredMethods();
        for (Method m : declaredMethods){
            Test annotation = m.getAnnotation(Test.class);
            System.out.println(annotation);
        }
        System.out.println(">>>>>>>>>>>>>>>> 类上的注解");
        Test annotation = cl.getAnnotation(Test.class);
        System.out.println(annotation);

    }

}
