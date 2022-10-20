package pers.mk.opspace.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pers.mk.opspace.aspect.MyLogAnnotation;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2022/10/9 14:21
 */
@Slf4j
@Controller
public class DefaultController {

    @GetMapping("/index")
    @MyLogAnnotation(ope = "首页",parameter = "")
    public String index(){
        System.out.println("66666666666666666666");
        return "/index";
    }
}
