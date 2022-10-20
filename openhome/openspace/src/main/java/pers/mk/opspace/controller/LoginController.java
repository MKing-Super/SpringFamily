package pers.mk.opspace.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @Description: springboot中session的数据存储
 * @Author: kun.ma
 * @Date: 2022/10/20 14:30
 */
@Slf4j
@Controller
@RequestMapping("/home")
public class LoginController {

    @Autowired
    private HttpSession session;


    @RequestMapping("/login")
    public String login(String name, String pwd){
        if ("admin".equals(name) && "123".equals(pwd)){
            session.setAttribute("name","admin");
        }
        Enumeration<String> attributeNames = session.getAttributeNames();
        System.out.println(session.getAttribute("name"));
        return "/home/login";

    }

}
