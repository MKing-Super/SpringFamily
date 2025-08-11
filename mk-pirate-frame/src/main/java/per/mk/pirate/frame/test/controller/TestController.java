package per.mk.pirate.frame.test.controller;

import per.mk.pirate.frame.PiratePart;
import per.mk.pirate.frame.PirateUrl;

import java.util.Date;

@PiratePart
public class TestController {

    @PirateUrl(value = {"/test"})
    public String testMethod(String name){
        return new Date().toString() + " test -> " + name;
    }

    @PirateUrl(value = {"/mk"})
    public String mkMethod(String name){
        return new Date().toString() + " mk -> " + name;
    }


}
