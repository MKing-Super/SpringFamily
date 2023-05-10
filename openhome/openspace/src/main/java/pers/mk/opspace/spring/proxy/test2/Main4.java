package pers.mk.opspace.spring.proxy.test2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import pers.mk.opspace.spring.proxy.test2.service.Service3;

/**
 * @describe: 使用cglib代理，对已有的方法不做拦截，被调用的方法没有被代理做任何处理，直接进到目标类Service3的方法中了。
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2
 * @Date 2023/5/9 14:21
 * @Version 1.0
 */
public class Main4 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 o = (Service3)enhancer.create();
        System.out.println(o.m1());
        System.out.println(o.m2());
    }
}
