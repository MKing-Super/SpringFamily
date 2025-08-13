package per.mk.pirate.test.listener;

import java.util.Date;

public abstract class BaseEvent {
    private final EventType type;
    private final Date timestamp;
    private final Object source;

    public BaseEvent(EventType type, Object source) {
        this.type = type;
        this.source = source;
        this.timestamp = new Date();
    }

    public EventType getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Object getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", timestamp=" + timestamp +
                ", source=" + source +
                '}';
    }
}