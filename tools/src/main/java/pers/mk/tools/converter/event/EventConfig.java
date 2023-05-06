package pers.mk.tools.converter.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.event
 * @Date 2023/5/4 11:28
 * @Version 1.0
 */
@Configuration
@ComponentScan
public class EventConfig {

    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners) { //@1
        EventMulticaster eventPublisher = new SimpleEventMulticaster();
        if (eventListeners != null) {
            for (EventListener er : eventListeners){
                eventPublisher.addEventListener(er);
            }
        }
        return eventPublisher;
    }

    @Bean
    public UserRegisterService userRegisterService(EventMulticaster eventMulticaster) { //@2
        UserRegisterService userRegisterService = new UserRegisterService();
        userRegisterService.setEventMulticaster(eventMulticaster);
        return userRegisterService;
    }

}
