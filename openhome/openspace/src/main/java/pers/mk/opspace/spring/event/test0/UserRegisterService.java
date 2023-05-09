package pers.mk.opspace.spring.event.test0;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.event.test0
 * @Date 2023/5/9 10:11
 * @Version 1.0
 */
@Component
public class UserRegisterService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void registerUser(String userName){
        System.out.println(userName + "（主要事务）注册成功::" + this.getClass().getPackage());
        this.applicationEventPublisher.publishEvent(new UserRegisterEvent(this,userName));
    }
}
