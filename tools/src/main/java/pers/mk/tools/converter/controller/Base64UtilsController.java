package pers.mk.tools.converter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.controller
 * @Date 2023/4/24 11:25
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/Base64Utils")
public class Base64UtilsController {

    @RequestMapping("/index")
    public String index(){
        return "/Base64Utils/index";
    }

    @RequestMapping("/encode")
    @ResponseBody
    public String encode(String encodeStr){
        return Base64Utils.encodeToString(encodeStr.getBytes());
    }

    @RequestMapping("/decode")
    @ResponseBody
    public String decode(String decodeStr){
        return new String(Base64Utils.decodeFromString(decodeStr), StandardCharsets.UTF_8);
    }


}
