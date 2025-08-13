package per.mk.pirate.test.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventPublisher {
    private static final EventPublisher INSTANCE = new EventPublisher();
    private final List<EventListener> listeners = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    private EventPublisher() {}

    public static EventPublisher getInstance() {
        return INSTANCE;
    }

    public synchronized void registerListener(EventListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public synchronized void unregisterListener(EventListener listener) {
        listeners.remove(listener);
    }

    public void publishEvent(BaseEvent event) {
        List<EventListener> currentListeners;
        synchronized (this) {
            currentListeners = new ArrayList<>(listeners);
        }

        for (EventListener listener : currentListeners) {
            if (listener.accept(event.getType())) {
                executor.submit(() -> {
                    try {
                        listener.onEvent(event);
                    } catch (Exception e) {
                        System.err.println("Error handling event: " + e.getMessage());
                    }
                });
            }
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
