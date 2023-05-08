package pers.mk.tools.converter.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.tools.converter.event.UserRegisterService;
import pers.mk.tools.converter.model.event.MsgEvent;
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
@RequiredArgsConstructor
public class EventController {

    private final UserRegisterService userRegisterService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        // 手动编写的事务监听
        userRegisterService.registerUser("mk");
    }

    @RequestMapping("/test1")
    @ResponseBody
    public void test1() {
        //使用spring提供的事务监听
        System.out.println("触发主要事务：：注册用户~");
        PushEvent pushEvent = new PushEvent(this, "次要事务执行结束");
        applicationEventPublisher.publishEvent(pushEvent);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public void test2() {
        //使用spring提供的事务监听
        MsgEvent event = new MsgEvent(this, "我炸了");
        applicationEventPublisher.publishEvent(event);
    }

}
