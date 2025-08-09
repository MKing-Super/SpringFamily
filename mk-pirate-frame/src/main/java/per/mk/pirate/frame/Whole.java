package per.mk.pirate.frame;

import java.util.Map;

public class Whole {

    // bean实例化存储
    private static Map<String, Object> beanMap;
    // http端口监听
    private static HttpServer httpServer;
    // 事件处理循环
    private static EventLoop eventLoop;


    public static Map<String, Object> getBeanMap() {
        return beanMap;
    }

    public static void setBeanMap(Map<String, Object> beanMap) {
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
