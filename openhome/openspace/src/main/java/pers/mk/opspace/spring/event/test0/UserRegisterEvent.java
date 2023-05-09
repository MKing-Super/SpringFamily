package pers.mk.opspace.spring.event.test0;

import org.springframework.context.ApplicationEvent;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test0
 * @Date 2023/5/9 10:08
 * @Version 1.0
 */
public class UserRegisterEvent extends ApplicationEvent {
    private String userName;

    public UserRegisterEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
