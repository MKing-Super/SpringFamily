package per.mk.pirate.frame;

import java.util.Map;

public class Ignition {

    public static void start(){
        try {
            // bean实例化存储
            Map<String, BeanInfo> beanMap = BeanRegister.start();

            // http端口监听
            System.out.println("http端口监听 启动");
            HttpServer httpServer = new HttpServer();
            httpServer.start();
            System.out.println("http端口监听 启动成功");

            // 事件处理循环
            System.out.println("事件处理循环 启动");
            EventLoop eventLoop = new EventLoop();
            eventLoop.start();
            System.out.println("事件处理循环 启动成功");

            // 整体
            Whole.setBeanMap(beanMap);
            Whole.setHttpServer(httpServer);
            Whole.setEventLoop(eventLoop);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
