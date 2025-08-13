package per.mk.pirate.test.listener;

public class UserBehaviorListener implements EventListener {
    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof UserLoginEvent) {
            UserLoginEvent loginEvent = (UserLoginEvent) event;
            System.out.println("[ANALYTICS] User " + loginEvent.getUsername() +
                    " logged in from " + loginEvent.getIpAddress());
        } else if (event instanceof OrderCreatedEvent) {
            OrderCreatedEvent orderEvent = (OrderCreatedEvent) event;
            System.out.println("[ANALYTICS] Order created: " + orderEvent.getOrderId() +
                    ", amount: " + orderEvent.getAmount());
        }
    }

    @Override
    public boolean accept(EventType eventType) {
        // 只处理用户相关事件
        return eventType == EventType.USER_LOGIN || eventType == EventType.ORDER_CREATED;
    }
}
