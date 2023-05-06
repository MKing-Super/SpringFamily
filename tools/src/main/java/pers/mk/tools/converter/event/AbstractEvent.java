package pers.mk.tools.converter.event;

/**
 * @describe: 事件对象
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.event
 * @Date 2023/5/4 10:26
 * @Version 1.0
 */
public abstract class AbstractEvent {

    private Object source;

    public AbstractEvent(Object source) {
        this.source = source;
    }

    public AbstractEvent() {
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
