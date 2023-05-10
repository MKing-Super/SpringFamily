package pers.mk.opspace.spring.proxy.test2;

import org.springframework.cglib.proxy.*;
import pers.mk.opspace.spring.proxy.test2.service.Service4;

import java.lang.reflect.Method;

/**
 * @describe: 使用cglib代理，不同方法使用不同的拦截器
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2
 * @Date 2023/5/9 17:47
 * @Version 1.0
 */
public class Main5 {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
        Callback[] callbacks = {
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        long start = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long end = System.nanoTime();
                        System.out.println(method + "方法耗时：：" + (end - start));
                        return result;
                    }
                },
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "我炸了";
                    }
                }
        };

        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                // 根据方法名称的前缀确定返回值（callbacks数组的下标）
                return method.getName().startsWith("insert") ? 0 : 1;
            }
        });

        Service4 proxy = (Service4) enhancer.create();
        proxy.insert1();
        proxy.insert2();
        System.out.println("-----------------------------");
        System.out.println(proxy.get1());
        System.out.println(proxy.get2());


    }

}
