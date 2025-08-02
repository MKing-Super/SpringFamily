package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello() {

        try {
            Class<?> aClass = Class.forName(String.valueOf(HelloController.class));
        }catch (Exception e){

        }


        return "Hello from Spring Boot!";
    }
}