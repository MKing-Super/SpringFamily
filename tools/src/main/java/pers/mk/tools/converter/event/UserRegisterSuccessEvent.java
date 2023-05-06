package pers.mk.tools.converter.event;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.event
 * @Date 2023/5/4 10:53
 * @Version 1.0
 */
public class UserRegisterSuccessEvent extends AbstractEvent{

    private String userName;

    public UserRegisterSuccessEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public UserRegisterSuccessEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
