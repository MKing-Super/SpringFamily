package pers.mk.opspace.spring.annotation.test11;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.test11
 * @Date 2023/5/10 11:30
 * @Version 1.0
 */
@Ann11("用在了类上")
@Ann11_0(0)
public class UseAnnotation11<@Ann11("用在了类变量类型V1上") @Ann11_0(1) V1, @Ann11("用在了类变量类型V2上") @Ann11_0(2) V2> {

    @Ann11("用在了字段上")
    @Ann11_0(3)
    private String name;

    private Map<@Ann11("用在了泛型类型上,String") @Ann11_0(4) String, @Ann11("用在了泛型类型上,Integer") @Ann11_0(5) Integer> map;

    @Ann11("用在了构造方法上")
    @Ann11_0(6)
    public UseAnnotation11() {
        this.name = name;
    }

    @Ann11("用在了返回值上")
    @Ann11_0(7)
    public String m1(@Ann11("用在了参数上") @Ann11_0(8) String name) {
        return null;
    }



    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("类上的注解：");
        m1();
        System.out.println("-------------------------------------");

        System.out.println("类上泛型的注解");
        m2();
        System.out.println("-----------------------------------------");

        System.out.println("类中变量上的注解信息：");
        m3();
        System.out.println("--------------------------------------------");


    }

    static void m3() throws NoSuchFieldException {
        Field name = UseAnnotation11.class.getDeclaredField("name");
        for (Annotation an : name.getAnnotations()){
            System.out.println(an);
        }
    }

    static void m2(){
        TypeVariable<Class<UseAnnotation11>>[] typeParameters = UseAnnotation11.class.getTypeParameters();
        for (TypeVariable ty : typeParameters){
            System.out.println(ty.getName());
            for (Annotation an : ty.getAnnotations()){
                System.out.println(an);
            }
        }
    }

    static void m1(){
        Annotation[] annotations = UseAnnotation11.class.getAnnotations();
        for (Annotation an : annotations){
            System.out.println(an);
        }
    }

}
