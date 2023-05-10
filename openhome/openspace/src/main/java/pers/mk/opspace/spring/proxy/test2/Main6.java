package pers.mk.opspace.spring.proxy.test2;

import org.springframework.cglib.proxy.*;
import pers.mk.opspace.spring.proxy.test2.service.Service4;

import java.lang.reflect.Method;

/**
 * @describe: 使用cglib代理，多个方法的区别代理
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy.test2
 * @Date 2023/5/10 9:07
 * @Version 1.0
 */
public class Main6 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        Callback costTimeCallback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(method + "::开始");
                Object result = methodProxy.invokeSuper(o, args);
                System.out.println(method + "::结束");
                return result;
            }
        };
        Callback fixedValueCallback = new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println(this.getClass().getName());
                return "我真的炸了";
            }
        };
        CallbackHelper callbackHelper = new CallbackHelper(Service4.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixedValueCallback;
            }
        };
        enhancer.setSuperclass(Service4.class);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        enhancer.setCallbackFilter(callbackHelper);
        Service4 proxy = (Service4) enhancer.create();
        proxy.insert2();
        proxy.insert1();
        System.out.println("-----------------");
        proxy.get1();

    }
}
