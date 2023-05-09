package pers.mk.opspace.spring.proxy.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test1
 * @Date 2023/5/9 11:39
 * @Version 1.0
 */
public class CostTimeInvocationHandler implements InvocationHandler {
    private Object target;

    public CostTimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(this.target, args);
        long end = System.nanoTime();
        System.out.println(this.target.getClass() + "::" + method.getName() + "方法耗时：：" + (end - start));
        return result;
    }

    public static <T> T createProxy(Object target, Class<T> targetInterface) {
        if (!targetInterface.isInterface()) {
            throw new IllegalStateException("targetInterface必须是接口类型!");
        } else if (!targetInterface.isAssignableFrom(target.getClass())) {
            throw new IllegalStateException("target必须是targetInterface接口的实现类!");
        }
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new CostTimeInvocationHandler(target));
    }

}
