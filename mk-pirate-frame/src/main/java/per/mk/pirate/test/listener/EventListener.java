package per.mk.pirate.test.listener;

public interface EventListener {
    void onEvent(BaseEvent event);

    // 默认实现：只处理特定类型的事件
    default boolean accept(EventType eventType) {
        return true;
    }
}
