package per.mk.pirate.test.frame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 各种测试
 */
public class Test {

    public static void main(String[] args) {
        try {
            // 方法 重载 测试
            String methodName = "testMethod";// 方法名
            // 参数数组
//            Object[] vars = {111};
//            Object[] vars = {"mk",666};
            Object[] vars = {"mkmk"};

            Class<?> aClass = Class.forName("per.mk.pirate.test.frame.controller.TestController");
            // 类 内容取值
            Constructor<?> constructor = aClass.getConstructor();
            Method[] methods = aClass.getMethods();
            // 类 实例化
            Object object = constructor.newInstance();
            for (Method method : methods){
                if (!methodName.equals(method.getName())){
                    break;
                }
//                System.out.println("方法：" + method.getName() + "，参数数（" + method.getParameterTypes().length + "）。");
                // 方法 参数类型取值
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 参数长度一致
                if (parameterTypes.length == vars.length){
                    // 参数类型一致
                    boolean isTypeSame = true;
                    for (int i = 0 ; i < vars.length ; i++){
                        Class<?> methodParam = parameterTypes[i];
                        Class<?> varsParam = vars[i].getClass();
                        if (!methodParam.getName().equals(varsParam.getName())){
                            isTypeSame = false;
                        }
                    }
                    if (isTypeSame){
                        // 1.直接调用
//                        Object invoke = method.invoke(object, vars);
//                        System.out.println(invoke);

                        // 2.动态代理
                        InvocationHandler handler = (target0, method0, params0) -> {
                            System.out.println("调用方法 前 执行: " + method0.getName());
                            Object result = method0.invoke(target0, params0);
                            System.out.println(result);
                            System.out.println("调用方法 后 执行: " + method0.getName());
                            return result;
                        };
                        try {
                            Object invoke = handler.invoke(object, method, vars);
                        } catch (Throwable throwable) {
                            System.out.println("调用方法 异常 执行");
                            throwable.printStackTrace();
                        }finally {
                            System.out.println("调用方法 最终 执行");
                        }

                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
