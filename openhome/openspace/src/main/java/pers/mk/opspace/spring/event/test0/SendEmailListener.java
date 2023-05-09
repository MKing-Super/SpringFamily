package pers.mk.opspace.spring.event.test0;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test0
 * @Date 2023/5/9 10:09
 * @Version 1.0
 */
@Component
public class SendEmailListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(event.getUserName() + "（次要事务）发邮件::" + this.getClass().getPackage());
    }
}
