package per.mk.pirate.frame;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class BeanRegister {

    // 指定扫描的包
    public static String PACKAGE_PATH = "per.mk.pirate.frame.test";
    // 存储实例bean
    public static Map<String,BeanInfo> beanRegisterMap = new ConcurrentHashMap<>();
    // @PiratePart 类 名称
    public static List<String> piratePartClasses = new ArrayList<>();

    public static Map<String,BeanInfo> start(){
        try {
            // 扫描指定的包
            scanPackage(PACKAGE_PATH);
            // 将带有 @PiratePart 的类实例化并存储
            for (String className : piratePartClasses){
                Class<?> clazz = Class.forName(className);
                Constructor<?> constructor = clazz.getConstructor();
                Object o = constructor.newInstance();

                // 将 实例化类中有 @PirateUrl 的方法存储
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(PirateUrl.class)) {
                        PirateUrl annotation = method.getAnnotation(PirateUrl.class);
                        String[] value = annotation.value();
                        for (String url : value){
                            if (beanRegisterMap.get(url) != null){
                                throw new Exception(url + " 路径不能重复！");
                            }
                            System.out.println("@PirateUrl 方法: url->" + url + " : method->" + method.getName());
                            beanRegisterMap.put(url,new BeanInfo(url,o,method));
                        }
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return beanRegisterMap;
        }

    }


    private static void scanPackage(String basePackage) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = basePackage.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<Class<?>> classes = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            // 兼容 开发环境 与 部署环境
            String protocol = resource.getProtocol(); // 获取协议类型
            if ("file".equals(protocol)) {
                // 文件系统路径（开发环境）
                File file = new File(resource.toURI());
                classes.addAll(findClasses(file, basePackage));// 递归查找子包
            } else if ("jar".equals(protocol)) {
                // JAR 包内路径（部署环境）
                classes.addAll(processJarResource(resource, basePackage));
            }

//            File file = new File(resource.toURI());
//            classes.addAll(findClasses(file, basePackage)); // 递归查找子包
        }

        // 检测类和方法上的@PiratePart
        for (Class<?> clazz : classes) {
            detectDeprecated(clazz);
        }
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) return classes;

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName())); // 递归子目录
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className)); // 加载类
            }
        }
        return classes;
    }

    private static void detectDeprecated(Class<?> clazz) {
        // 检测类是否 @PiratePart
        if (clazz.isAnnotationPresent(PiratePart.class)) {
            System.out.println("@PiratePart 类: " + clazz.getName());
            piratePartClasses.add(clazz.getName());
        }else {
            return;
        }

        // 检测方法是否 @PirateUrl
//        for (Method method : clazz.getDeclaredMethods()) {
//            if (method.isAnnotationPresent(PirateUrl.class)) {
//                PirateUrl annotation = method.getAnnotation(PirateUrl.class);
//                System.out.println(annotation);
//                String[] value = annotation.value();
//                for (String url : value){
//                    pirateUrlMap.put(url,method);
//                    System.out.println("@PirateUrl 方法: url->" + url + " : method->" + method.getName());
//                }
//            }
//        }
    }

    private static List<Class<?>> processJarResource(URL jarUrl, String basePackage) throws IOException {
        System.out.println(jarUrl);
        List<Class<?>> classes = new ArrayList<>();
        String jarPath = jarUrl.getPath().split("!")[0].replace("jar:", "").replace("file:/", "");; // 提取 JAR 文件路径
        String packagePath = basePackage.replace('.', '/'); // 包路径（如 per/mk/pirate/frame/test）
        System.out.println(jarPath);

        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();

                // 过滤：匹配目标包路径且是 .class 文件
                if (entryName.startsWith(packagePath) && entryName.endsWith(".class")) {
                    String className = entryName
                            .replace("/", ".") // 路径转包名
                            .replace(".class", ""); // 去除后缀
                    try {
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    } catch (ClassNotFoundException e) {
                        // 忽略加载失败的类
                    }
                }
            }
        }
        return classes;
    }


}

class BeanInfo {
    String url;
    Object object;
    Method method;

    public BeanInfo(String url, Object object, Method method) {
        this.url = url;
        this.object = object;
        this.method = method;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}