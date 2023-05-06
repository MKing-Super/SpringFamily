package pers.mk.tools.converter.event.listener;

import org.springframework.stereotype.Component;
import pers.mk.tools.converter.event.EventListener;
import pers.mk.tools.converter.event.UserRegisterSuccessEvent;

/**
 * @describe: 扫描注册到spring容器
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.event
 * @Date 2023/5/4 11:42
 * @Version 1.0
 */
@Component
public class SendEmailOnUserRegisterSuccessListener implements EventListener<UserRegisterSuccessEvent> {
    @Override
    public void onEvent(UserRegisterSuccessEvent event) {
        System.out.println(
                String.format("给用户【%s】发送注册成功邮件!", event.getUserName()));
    }
}
