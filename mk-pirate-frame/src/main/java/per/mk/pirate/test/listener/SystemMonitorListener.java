package per.mk.pirate.test.listener;

public class SystemMonitorListener implements EventListener {
    @Override
    public void onEvent(BaseEvent event) {
        if (event.getType() == EventType.SYSTEM_SHUTDOWN) {
            System.out.println("[MONITOR] System shutdown detected. Performing cleanup...");
            // 执行清理操作
        } else if (event.getType() == EventType.USER_LOGIN) {
            System.out.println("[MONITOR] User login activity detected");
        }
    }
}