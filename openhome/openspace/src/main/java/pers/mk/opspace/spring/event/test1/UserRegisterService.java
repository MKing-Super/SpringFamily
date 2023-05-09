package pers.mk.opspace.spring.event.test1;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import pers.mk.opspace.spring.event.test0.UserRegisterEvent;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test1
 * @Date 2023/5/9 10:46
 * @Version 1.0
 */
@Component
public class UserRegisterService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void reg(String userName){
        System.out.println(userName + "注册成功：：" + this.getClass().getPackage());
        this.applicationEventPublisher.publishEvent(new UserRegisterEvent(this,userName));
    }
}
