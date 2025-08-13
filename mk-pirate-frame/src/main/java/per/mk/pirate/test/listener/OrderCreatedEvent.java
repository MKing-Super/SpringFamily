package per.mk.pirate.test.listener;

// 订单创建事件
public class OrderCreatedEvent extends BaseEvent {
    private final String orderId;
    private final double amount;

    public OrderCreatedEvent(Object source, String orderId, double amount) {
        super(EventType.ORDER_CREATED, source);
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }
}