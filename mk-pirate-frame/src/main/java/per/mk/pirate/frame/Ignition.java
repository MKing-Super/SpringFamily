package per.mk.pirate.frame;

import per.mk.pirate.frame.bean.BeanInfo;
import per.mk.pirate.frame.bean.BeanRegister;
import per.mk.pirate.frame.event.EventLoop;
import per.mk.pirate.frame.socket.HttpServer;
import per.mk.pirate.frame.whole.Whole;

import java.util.Map;
import java.util.Properties;

/**
 框架启动类

 修改 BeanRegister.PACKAGE_PATH 指定扫包路径
 修改 HttpServer.HTTP_PORT 指定httpServer监视接口

 项目启动 Ignition.start()
 **/
public class Ignition {

    public static void start(){
        try {
            // 1.项目启动耗时
            long startTime = System.nanoTime();
            // 2.设置无头模式
            System.setProperty("java.awt.headless", "true");


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
            long endTime = System.nanoTime();
            System.out.println("主线程执行耗时: " + (endTime - startTime) / 1_000_000 + " ms");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
