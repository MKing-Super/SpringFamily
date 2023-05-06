package pers.mk.tools.converter.event;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.event
 * @Date 2023/5/4 10:22
 * @Version 1.0
 */
public interface EventListener <E extends AbstractEvent>{

    void onEvent(E event);

}
