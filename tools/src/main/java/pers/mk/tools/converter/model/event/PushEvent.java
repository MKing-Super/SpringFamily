package pers.mk.tools.converter.model.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.model.event
 * @Date 2023/5/6 9:55
 * @Version 1.0
 */
public class PushEvent extends ApplicationEvent {

    private String msg;

    public PushEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
