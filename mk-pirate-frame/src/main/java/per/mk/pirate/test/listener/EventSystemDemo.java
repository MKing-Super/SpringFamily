package per.mk.pirate.test.listener;

public class EventSystemDemo {

    public static void main(String[] args) {
        // 获取事件发布器单例
        EventPublisher publisher = EventPublisher.getInstance();

        // 注册各种监听器
        publisher.registerListener(new LoggingListener());

        // 模拟用户登录
        Object authService = new Object(); // 模拟认证服务
        publisher.publishEvent(new UserLoginEvent(authService, "john_doe", "192.168.1.100"));


        // 关闭事件发布器
        try {
            Thread.sleep(1000); // 等待事件处理完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        publisher.shutdown();
        //

    }

}
