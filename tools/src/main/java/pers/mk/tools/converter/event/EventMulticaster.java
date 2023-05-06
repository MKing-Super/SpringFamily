package pers.mk.tools.converter.event;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.event
 * @Date 2023/5/4 10:31
 * @Version 1.0
 */
public interface EventMulticaster {

    void multicastEvent(AbstractEvent event);

    void addEventListener(EventListener<?> listener);

    void removeEventListener(EventListener<?> listener);

}
