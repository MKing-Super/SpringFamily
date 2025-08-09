package per.mk.pirate.frame;

public class Ignition {

    public static void start(){
        try {

            BeanRegister.start();


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

            httpServer.eventLoop = eventLoop;

            // 启动测试
            Event event = new Event() {
                @Override
                public void handle() {
                    System.out.println("11");
                }
            };
            eventLoop.postEvent(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
