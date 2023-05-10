package pers.mk.opspace.spring.proxy.test2.service;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2.service
 * @Date 2023/5/9 14:12
 * @Version 1.0
 */
public class Service2 {
    public void m1(){
        System.out.println("I am m1 method");
        this.m2();
    }

    public void m2(){
        System.out.println("I am m2 method");
    }
}
