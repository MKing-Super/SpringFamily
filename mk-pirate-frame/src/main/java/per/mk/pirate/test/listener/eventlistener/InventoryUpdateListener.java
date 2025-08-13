package per.mk.pirate.test.listener.eventlistener;

import per.mk.pirate.test.listener.enums.EventType;
import per.mk.pirate.test.listener.event.BaseEvent;
import per.mk.pirate.test.listener.event.OrderCreatedEvent;

public class InventoryUpdateListener implements EventListener {
    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof OrderCreatedEvent) {
            OrderCreatedEvent orderEvent = (OrderCreatedEvent) event;
            System.out.println("[INVENTORY] Processing inventory update for order: " +
                    orderEvent.getOrderId());
            // 实际业务中这里会更新库存
        }
    }

    @Override
    public boolean accept(EventType eventType) {
        return eventType == EventType.ORDER_CREATED;
    }
}