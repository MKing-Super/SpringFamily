package per.mk.pirate.test.listener;

// 用户登录事件
public class UserLoginEvent extends BaseEvent {
    private final String username;
    private final String ipAddress;

    public UserLoginEvent(Object source, String username, String ipAddress) {
        super(EventType.USER_LOGIN, source);
        this.username = username;
        this.ipAddress = ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}