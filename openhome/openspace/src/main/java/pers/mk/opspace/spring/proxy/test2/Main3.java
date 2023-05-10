package pers.mk.opspace.spring.proxy.test2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import pers.mk.opspace.spring.proxy.test2.service.Service3;

/**
 * @describe: 使用cglib代理，返回固定的值
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2
 * @Date 2023/5/9 14:17
 * @Version 1.0
 */
public class Main3 {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "路人甲";
            }
        });
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());//@1
        System.out.println(proxy.m2()); //@2
        System.out.println(proxy.toString());//@3
    }

}
