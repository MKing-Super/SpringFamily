package pers.mk.opspace.spring.proxy.test1;

import pers.mk.opspace.spring.proxy.test0.ISService;
import pers.mk.opspace.spring.proxy.test0.ISServiceA;
import pers.mk.opspace.spring.proxy.test0.ISServiceB;

/**
 * @describe: JDK动态代理，统计每个方法的耗时
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test1
 * @Date 2023/5/9 13:06
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ISService proxyA = CostTimeInvocationHandler.createProxy(new ISServiceA(), ISService.class);
        proxyA.m1();
        proxyA.m2();
        proxyA.m3();

        System.out.println("----------------------------------------");

        ISService proxyB = CostTimeInvocationHandler.createProxy(new ISServiceB(), ISService.class);
        proxyB.m2();
    }

}
