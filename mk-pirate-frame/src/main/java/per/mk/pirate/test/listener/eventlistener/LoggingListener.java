package per.mk.pirate.test.listener.eventlistener;

import per.mk.pirate.test.listener.enums.EventType;
import per.mk.pirate.test.listener.event.BaseEvent;

public class LoggingListener implements EventListener {
    @Override
    public void onEvent(BaseEvent event) {
        System.out.println("[LOG] " + event.getTimestamp() + " - " + event.getType() + ": " + event);
    }

    @Override
    public boolean accept(EventType eventType) {
        // 记录所有类型的事件
        return true;
    }
}