package pers.mk.opspace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.mk.api.model.Son1;
import pers.mk.opspace.service.impl.EventServiceImpl;
import pers.mk.opspace.spring.di.SonListener;

import java.util.List;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.controller
 * @Date 2023/5/8 13:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        eventService.startEvent(new Son1(null,"我炸了"));
        return "success";
    }

}
