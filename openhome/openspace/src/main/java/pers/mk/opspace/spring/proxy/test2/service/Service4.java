package pers.mk.opspace.spring.proxy.test2.service;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2.service
 * @Date 2023/5/9 17:48
 * @Version 1.0
 */
public class Service4 {
    public void insert1() {
        System.out.println("我是insert1");
    }

    public void insert2() {
        System.out.println("我是insert2");
    }

    public String get1() {
        System.out.println("我是get1");
        return "get1";
    }

    public String get2() {
        System.out.println("我是get2");
        return "get2";
    }
}
