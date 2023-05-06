package pers.mk.tools.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.tools.converter.event.UserRegisterService;

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
public class EventController {

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        userRegisterService.registerUser("mk");
    }

}
