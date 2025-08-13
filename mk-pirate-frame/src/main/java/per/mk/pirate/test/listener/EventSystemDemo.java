package per.mk.pirate.test.listener;

import per.mk.pirate.test.listener.enums.EventType;
import per.mk.pirate.test.listener.event.BaseEvent;
import per.mk.pirate.test.listener.event.OrderCreatedEvent;
import per.mk.pirate.test.listener.event.UserLoginEvent;
import per.mk.pirate.test.listener.eventlistener.InventoryUpdateListener;
import per.mk.pirate.test.listener.eventlistener.LoggingListener;
import per.mk.pirate.test.listener.eventlistener.SystemMonitorListener;
import per.mk.pirate.test.listener.eventlistener.UserBehaviorListener;
import per.mk.pirate.test.listener.eventpublisher.EventPublisher;

public class EventSystemDemo {
    public static void main(String[] args) throws InterruptedException {
        // 获取事件发布器单例
        EventPublisher publisher = EventPublisher.getInstance();

        // 注册各种监听器
        publisher.registerListener(new LoggingListener());
        publisher.registerListener(new UserBehaviorListener());
        publisher.registerListener(new InventoryUpdateListener());
        publisher.registerListener(new SystemMonitorListener());

        // 模拟用户登录
        Object authService = new Object(); // 模拟认证服务
        publisher.publishEvent(new UserLoginEvent(authService, "john_doe", "192.168.1.100"));

        Thread.sleep(1000);

        // 模拟订单创建
        Object orderService = new Object(); // 模拟订单服务
        publisher.publishEvent(new OrderCreatedEvent(orderService, "ORD12345", 99.99));

        Thread.sleep(1000);

        // 模拟系统关闭
        publisher.publishEvent(new BaseEvent(EventType.SYSTEM_SHUTDOWN, EventSystemDemo.class) {
            @Override
            public String toString() {
                return "System shutdown initiated";
            }
        });

        // 关闭事件发布器
        try {
            Thread.sleep(1000); // 等待事件处理完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        publisher.shutdown();
    }
}
