package pers.mk.opspace.spring.proxy.test2.service;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2.service
 * @Date 2023/5/9 14:18
 * @Version 1.0
 */
public class Service3 {
    public String m1() {
        System.out.println("我是m1方法");
        return "hello:m1";
    }

    public String m2() {
        System.out.println("我是m2方法");
        return "hello:m2";
    }
}
