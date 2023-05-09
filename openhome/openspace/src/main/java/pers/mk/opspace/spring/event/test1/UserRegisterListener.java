package pers.mk.opspace.spring.event.test1;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pers.mk.opspace.spring.event.test0.UserRegisterEvent;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test1
 * @Date 2023/5/9 10:42
 * @Version 1.0
 */
@Component
public class UserRegisterListener {

    @EventListener
    public void sendMail(UserRegisterEvent event){
        System.out.println(event.getUserName() + "（次要事务）发邮件::" + this.getClass().getPackage());
    }

    @EventListener
    public void sendMessage(UserRegisterEvent event){
        System.out.println(event.getUserName() + "（次要事务）发信息::" + this.getClass().getPackage());
    }

}
