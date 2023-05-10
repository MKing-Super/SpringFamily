package pers.mk.opspace.spring.proxy.test2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import pers.mk.opspace.spring.proxy.test2.service.Service1;

import java.lang.reflect.Method;

/**
 * @describe: 使用cglib代理，任意方法的耗时统计代理
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2
 * @Date 2023/5/10 9:25
 * @Version 1.0
 */
public class Main7 implements MethodInterceptor {
    private Object target;

    public Main7(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, objects);
        long end = System.nanoTime();
        System.out.println(method + "::耗时" + (end - start));
        return result;
    }

    private static <T> T createProxy(T target){
        Main7 costTimeProxy = new Main7(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(costTimeProxy);
        enhancer.setSuperclass(target.getClass());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        Service1 proxy = Main7.createProxy(new Service1());
        proxy.m1();
    }
}
