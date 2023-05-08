package pers.mk.tools.converter.model.event;

import org.springframework.context.ApplicationEvent;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.model.event
 * @Date 2023/5/8 10:00
 * @Version 1.0
 */
public class MsgEvent extends ApplicationEvent {
    private String msg;

    public MsgEvent(Object source, String msg) {
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
