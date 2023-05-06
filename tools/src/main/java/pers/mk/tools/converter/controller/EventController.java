package pers.mk.tools.converter.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.tools.converter.event.UserRegisterService;
import pers.mk.tools.converter.model.event.PushEvent;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.controller
 * @Date 2023/5/4 13:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private UserRegisterService userRegisterService;
    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        userRegisterService.registerUser("mk");
    }

    @RequestMapping("/test1")
    @ResponseBody
    public void test1(){
        String msg = "mk";
        PushEvent pushEvent = new PushEvent(this, msg);
        applicationContext.publishEvent(pushEvent);
        applicationEventPublisher.publishEvent(pushEvent);
    }

}
