package pers.mk.opspace.spring.proxy;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy
 * @Date 2023/5/8 17:30
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ISServiceProxy a = new ISServiceProxy(new ISServiceA());
        a.m1();
        a.m2();
        a.m3();

        System.out.println();

        ISServiceProxy b = new ISServiceProxy(new ISServiceB());
        b.m1();
        b.m2();
    }

}
