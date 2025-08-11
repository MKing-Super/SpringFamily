package per.mk.pirate.frame.test.controller;

import per.mk.pirate.frame.PiratePart;
import per.mk.pirate.frame.PirateUrl;

import java.util.Date;

@PiratePart
public class TestController {

    @PirateUrl(value = {"/test"})
    public String testMethod(String name){
//        int a = 1/0;
        return "test(String) -> " + name;
    }

    public String testMethod(String name,Integer code){
        return "test(String,Integer) -> " + name + " | " + code;
    }

    public String testMethod(Integer code){
        return "test(Integer) -> " + code;
    }

    @PirateUrl(value = {"/mk"})
    public String mkMethod(String name){
        return new Date().toString() + " mk -> " + name;
    }


}
