package per.mk.pirate.frame.whole;

import per.mk.pirate.frame.bean.BeanInfo;
import per.mk.pirate.frame.event.EventLoop;
import per.mk.pirate.frame.socket.HttpServer;

import java.util.Map;

public class Whole {

    // bean实例化存储
    private static Map<String, BeanInfo> beanMap;
    // http端口监听
    private static HttpServer httpServer;
    // 事件处理循环
    private static EventLoop eventLoop;



    public static Map<String, BeanInfo> getBeanMap() {
        return beanMap;
    }

    public static void setBeanMap(Map<String, BeanInfo> beanMap) {
        Whole.beanMap = beanMap;
    }

    public static HttpServer getHttpServer() {
        return httpServer;
    }

    public static void setHttpServer(HttpServer httpServer) {
        Whole.httpServer = httpServer;
    }

    public static EventLoop getEventLoop() {
        return eventLoop;
    }

    public static void setEventLoop(EventLoop eventLoop) {
        Whole.eventLoop = eventLoop;
    }
}
