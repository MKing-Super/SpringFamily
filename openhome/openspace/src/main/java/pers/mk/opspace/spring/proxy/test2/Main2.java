package pers.mk.opspace.spring.proxy.test2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import pers.mk.opspace.spring.proxy.test2.service.Service2;

import java.lang.reflect.Method;

/**
 * @describe: 使用cglib代理，对类进行操作
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2
 * @Date 2023/5/9 14:12
 * @Version 1.0
 */
public class Main2 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法:" + method);
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        Service2 proxy = (Service2) enhancer.create();
        proxy.m1();
    }
}
